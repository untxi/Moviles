/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdio.h>
#include <vector>
#include <opencv2/opencv.hpp>


using namespace cv;
using namespace std;


/* Header for class amysa_mobileproject_alar_alar_OpenCVNativeClass */

#ifndef _Included_amysa_mobileproject_alar_alar_OpenCVNativeClass
#define _Included_amysa_mobileproject_alar_alar_OpenCVNativeClass
#ifdef __cplusplus
extern "C" {
#endif
 int toGray(Mat img,Mat&gray);
 int load(Mat image);
 int countQuestion(Mat image);
 void getAnwersGroup();
 void getAnswer(Mat group,int add);
 int compareImages(Mat base,Mat test1);


JNIEXPORT jint JNICALL Java_amysa_mobileproject_alar_alar_OpenCVNativeClass_loadImages
        (JNIEnv *, jclass,jlong,jlong,jlong,jlong,jlong,jlong);
JNIEXPORT jint JNICALL Java_amysa_mobileproject_alar_alar_OpenCVNativeClass_getAnswer
        (JNIEnv *, jclass, jint);
JNIEXPORT jint JNICALL Java_amysa_mobileproject_alar_alar_OpenCVNativeClass_convertGray
        (JNIEnv *, jclass, jlong, jlong);


#ifdef __cplusplus
}
#endif
#endif