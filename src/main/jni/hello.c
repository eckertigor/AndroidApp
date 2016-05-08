#include <jni.h>
#include <json.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
//#include <iostream>
#include "string.h"

#include<stdio.h>   //printf
//#include<string.h>    //strlen
#include<sys/socket.h>  //socket
#include<arpa/inet.h>   //inet_addr
#include "json_object.h"

JNIEXPORT jstring JNICALL
Java_com_example_ekkert_myapplication_MainActivity_stringFromJNI(JNIEnv *env, jobject instance, int numberPickerMinValue,
                                                                 int numberPickerMaxValue, jintArray jArray) {
    int sock;
    struct sockaddr_in server;
    int minWeather = numberPickerMinValue;
    int maxWeather = numberPickerMaxValue;
    jstring result = 0;

    json_object * jobj = json_object_new_object();
    json_object *jlower_temp = json_object_new_int(minWeather);
    json_object *jupper_temp = json_object_new_int(maxWeather);
    json_object *jtype = json_object_new_int(1);
    json_object *jactivity = json_object_new_array();
    int sizeA = (int)(*env)->GetArrayLength(env, jArray);
    jint *activityNum = (*env)->GetIntArrayElements(env, jArray, 0);

    json_object *array[sizeA];
    int i;
    for (i = 0; i < sizeA; i++) {
        array[i] = json_object_new_int((int) activityNum[i]);
        json_object_array_put_idx(jactivity, i, array[i]);
    }

    json_object_object_add(jobj,"type", jtype);
    json_object_object_add(jobj,"lt", jlower_temp);
    json_object_object_add(jobj,"ut", jupper_temp);
    json_object_object_add(jobj,"act", jactivity);
   // size_t *header = (size_t *)malloc(sizeof(size_t));
    int *headerr = (int *)malloc(sizeof(int));

    //*header = strlen(json_object_to_json_string(jobj));
    *headerr = strlen(json_object_to_json_string(jobj));
    // json_object *jactivites_temp = json_object_new_string(checked);

    //json_object_object_add(jobj,"items", jactivites_temp);

    //Create socket
    sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1)
    {
        return (*env)->NewStringUTF(env, "Could not create socket");
    }
    //puts("Socket created");

    server.sin_addr.s_addr = inet_addr("192.168.199.16");
    server.sin_family = AF_INET;
    server.sin_port = htons( 5002 );

    //Connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0)
    {
        return (*env)->NewStringUTF(env, "Connection to server failed");

    }
    //keep communicating with server
    if (write(sock, headerr, sizeof(int)) < 0) {
        return (*env)->NewStringUTF(env, "send header failed");
    }

    if (write(sock, json_object_to_json_string(jobj), strlen(json_object_to_json_string(jobj))) < 0) {
        return (*env)->NewStringUTF(env, "send failed");
    }
   // int *headerr = (int *)malloc(sizeof(int));

    int n = read(sock,headerr, sizeof(int));
    if (n < 0) {
        return (*env)->NewStringUTF(env, "ERROR reading header from socket");
    }
    char *response = (char *)malloc(*headerr);
    int k = read(sock, response, *headerr);
    if (k < 0) {
        return (*env)->NewStringUTF(env, "ERROR reading from socket");
    }
    //close(sock);
    //free(*headerr);
   // shutdown(sock, 2);
    return (*env)->NewStringUTF(env, response);

    return 0;

    //return (*env)->NewStringUTF(env, "dsds");
}