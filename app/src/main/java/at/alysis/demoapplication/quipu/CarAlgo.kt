package at.alysis.demoapplication.quipu

import javax.inject.Inject

class CarAlgo @Inject constructor(): CarAlgoDef {

    external override fun setInvalid()

    external override fun setCalibration(calibration: Float): Boolean

    external override fun getCalibration(): Float

    external override fun getDiameter(): Float

    external override fun getImt(): Float

    external override fun fps(): Double

    external override fun setFps(fps: Double)

    external override fun imtThreshold(): Int

    external override fun setImtThreshold(imtThreshold: Int): Boolean

    external override fun setROI(
        roiCenterX: Int,
        roiCenterY: Int,
        roiWidth: Int,
        roiHeight: Int,
        roiAngle: Double,
        imageWidth: Int,
        imageHeight: Int
    ): Boolean

    companion object {
        init {
            //System.loadLibrary("CarAlgo")
            //System.loadLibrary("native-lib")
        }
    }
}