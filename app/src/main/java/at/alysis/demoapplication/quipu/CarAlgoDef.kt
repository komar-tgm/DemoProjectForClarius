package at.alysis.demoapplication.quipu

interface CarAlgoDef {

    fun setInvalid()
    fun setCalibration(calibration: Float): Boolean
    fun getCalibration(): Float
    fun getDiameter(): Float
    fun getImt(): Float
    fun fps(): Double
    fun setFps(fps: Double)
    fun imtThreshold(): Int
    fun setImtThreshold(imtThreshold: Int): Boolean
    fun setROI(
        roiCenterX: Int,
        roiCenterY: Int,
        roiWidth: Int,
        roiHeight: Int,
        roiAngle: Double,
        imageWidth: Int,
        imageHeight: Int
    ): Boolean
}
