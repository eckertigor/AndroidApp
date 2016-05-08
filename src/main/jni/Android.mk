LOCAL_PATH := $(call my-dir)
LOCAL_ALLOW_UNDEFINED_SYMBOLS := true

include $(CLEAR_VARS)

LOCAL_MODULE    := json
LOCAL_C_INCLUDES := $(LOCAL_PATH)
LOCAL_SRC_FILES := arraylist.c \
                   	debug.c \
                   	json_c_version.c \
                   	json_object.c \
                   	json_object_iterator.c \
                   	json_tokener.c \
                   	json_util.c \
                   	linkhash.c \
                   	printbuf.c \
                   	random_seed.c
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)

LOCAL_MODULE    := hello
LOCAL_SRC_FILES := hello.c

include $(BUILD_SHARED_LIBRARY)
