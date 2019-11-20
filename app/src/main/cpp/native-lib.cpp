#include <jni.h>
#include <string>
#include <media/NdkMediaExtractor.h>
#include <android/log.h>
#include <bitset>

#define APP_NAME "MediaStoreToNativeAudio"
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, APP_NAME, __VA_ARGS__))

extern "C" JNIEXPORT void JNICALL
Java_com_example_mediastoretonativeaudio_MainActivity_parcelFileDescToJNI(
        JNIEnv *env,
        jobject jobj,
        jint fd,
        jlong offset,
        jlong length) {

    int fd_ = fd;
    int64_t offset_ = offset;
    int64_t length_ = length;
    AMediaExtractor *extractor = AMediaExtractor_new();
    media_status_t amresult = AMediaExtractor_setDataSourceFd(extractor, fd_, offset_, length_);
    if (amresult != AMEDIA_OK) {
        LOGE("AMediaExtractor_setDataSource called with: [%d-%ld-%ld]", fd_, offset_, length_);
        LOGE("Error setting extractor data source, err %d", amresult);
    }
    else {
        LOGE("AMediaExtractor_setDataSource called with: [%d-%ld-%ld]",fd_, offset_, length_);
        LOGE("SUCCESS !!! %d", amresult);
    }

    return;
}


