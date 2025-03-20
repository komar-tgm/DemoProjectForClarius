//
// Created by Karim Omar on 25.02.25.
//
#include <jni.h>
#include "CarAlgo.h"

// Create a global instance of CarAlgo
CarAlgo carAlgo;

extern "C"
JNIEXPORT void JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_setInvalid(JNIEnv *env, jobject thiz) {
    carAlgo.setInvalid();
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_setCalibration(JNIEnv *env, jobject thiz, jfloat calibration) {
    return carAlgo.setCalibration(calibration);
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_getCalibration(JNIEnv *env, jobject thiz) {
    return carAlgo.calibration();
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_getDiameter(JNIEnv *env, jobject thiz) {
    return carAlgo.getDiameter();
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_getImt(JNIEnv *env, jobject thiz) {
    return carAlgo.getImt();
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_fps(JNIEnv *env, jobject thiz) {
    return carAlgo.fps();
}

extern "C"
JNIEXPORT void JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_setFps(JNIEnv *env, jobject thiz, jdouble fps) {
    carAlgo.setFps(fps);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_setImtThreshold(JNIEnv *env, jobject thiz, jint imtThreshold) {
    return carAlgo.setImtThreshold(imtThreshold);
}

extern "C"
JNIEXPORT jint JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_imtThreshold(JNIEnv *env, jobject thiz) {
    return carAlgo.imtThreshold();
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_at_alysis_demoapplication_quipu_CarAlgo_setROI(JNIEnv *env, jobject thiz, jint roiCenterX, jint roiCenterY, jint roiWidth, jint roiHeight, jdouble roiAngle, jint imageWidth, jint imageHeight) {
    return carAlgo.setROI(roiCenterX, roiCenterY, roiWidth, roiHeight, roiAngle, imageWidth, imageHeight);
}
