package at.alysis.demoapplication.service.clarius;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.clarius.sdk.Button;
import me.clarius.sdk.Cast;
import me.clarius.sdk.Platform;
import me.clarius.sdk.PosInfo;
import me.clarius.sdk.ProcessedImageInfo;
import me.clarius.sdk.RawImageInfo;
import me.clarius.sdk.SpectralImageInfo;

public class CastService extends Service {
    private static final String TAG = "CastService";
    private static final String NONE = "<none>";
    private final MutableLiveData<Bitmap> processedImage = new MutableLiveData<>();
    private final MutableLiveData<Long> imageTime = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Integer> rawDataProgress = new MutableLiveData<>();
    private final IBinder binder = new CastBinder();
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final ImageConverter converter = new ImageConverter(executorService, new ImageConverter.Callback() {
        @Override
        public void onResult(Bitmap bitmap, long timestamp) {
            processedImage.postValue(bitmap);
            imageTime.postValue(timestamp);
        }

        @Override
        public void onError(Exception e) {
            error.postValue(e.toString());
        }
    });
    private final Cast.Listener listener = new Cast.Listener() {

        @Override
        public void error(String e) {
            Log.d(TAG,"Error: "+e);
            error.postValue(e);
        }

        @Override
        public void freeze(boolean frozen) {
            Log.d(TAG, "Freeze: " + frozen);
        }

        @Override
        public void newProcessedImage(ByteBuffer data, ProcessedImageInfo info, PosInfo[] pos) {
            converter.convertImage(data, info);
        }

        @Override
        public void newRawImageFn(ByteBuffer data, RawImageInfo info, PosInfo[] pos) {
        }

        @Override
        public void newSpectralImageFn(ByteBuffer data, SpectralImageInfo info) {
        }

//        @Override
//        public void newImuDataFn(PosInfo[] posInfos) {
//
//        }

        @Override
        public void newImuDataFn(PosInfo posInfo) {

        }

        @Override
        public void progress(int i) {
            rawDataProgress.postValue(i);
        }

        @Override
        public void buttonPressed(Button button, int count) {
            Log.d(TAG, "Button '" + button + " pressed " + count + " time(s)");
        }
    };
    private Cast cast;

    private static String getCertDir(Context context) {
        return context.getDir("cert", Context.MODE_PRIVATE).toString();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (cast == null) {
            Log.d(TAG, "Creating the Cast service");
            String nativeDir = getApplicationContext().getApplicationInfo().nativeLibraryDir;
            cast = new Cast(nativeDir, listener);
            String cert = getCertDir(this);
            Log.d(TAG, "CERT: "+cert);
            cast.initialize(cert, new Cast.BooleanResult() {
                @Override
                public void accept(boolean result) {
                    Log.d(TAG, "Initialization result: " + result);
                    if (result) {
                        cast.getFirmwareVersion(Platform.V1, optional -> Log.i(TAG, "Firmware " + Platform.V1 + ": " + optional.orElse(NONE)));
                        cast.getFirmwareVersion(Platform.HD, optional -> Log.i(TAG, "Firmware " + Platform.HD + ": " + optional.orElse(NONE)));
                        cast.getFirmwareVersion(Platform.HD3, optional -> Log.i(TAG, "Firmware " + Platform.HD3 + ": " + optional.orElse(NONE)));
                    }
                }
            });
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cast != null) {
            Log.d(TAG, "Destroying the Cast service");
            cast.disconnect(null);
            cast.release();
            cast = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Binding to the Cast service");
        return binder;
    }

    /**
     * The Binder is the interface to our service.
     */
    public class CastBinder extends Binder {
        public Cast getCast() {
            return cast;
        }

        public MutableLiveData<Bitmap> getProcessedImage() {
            return processedImage;
        }

        public MutableLiveData<Long> getTimestamp() {
            return imageTime;
        }

        public MutableLiveData<String> getError() {
            return error;
        }

        public MutableLiveData<Integer> getRawDataProgress() {
            return rawDataProgress;
        }
    }
}
