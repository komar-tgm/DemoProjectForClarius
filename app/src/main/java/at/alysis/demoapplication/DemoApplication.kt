package at.alysis.demoapplication

import android.app.Application
import at.alysis.demoapplication.quipu.CarAlgoDef
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DemoApplication: Application() {
    @Inject
    lateinit var carAlgoDef: CarAlgoDef
}