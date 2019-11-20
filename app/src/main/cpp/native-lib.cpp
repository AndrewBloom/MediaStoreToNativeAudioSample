#include <jni.h>
#include <string>
#include <media/NdkMediaExtractor.h>
#include <android/log.h>
#include <bitset>

#define APP_NAME "MediaStoreToNativeAudio"
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, APP_NAME, __VA_ARGS__))

extern "C" JNIEXPORT void JNICALL
Java_com_example_mediastoretonativeaudio_MainActivity_stringToJNI(
        JNIEnv *env,
        jobject jobj,
        jstring URI) {

    const char *uri = env->GetStringUTFChars(URI, NULL);
    std::string s(uri);

    AMediaExtractor *extractor = AMediaExtractor_new();
    media_status_t amresult = AMediaExtractor_setDataSource(extractor, uri);
    if (amresult != AMEDIA_OK) {
        LOGE("AMediaExtractor_setDataSource called with: [%s]", s.c_str());
        LOGE("Error setting extractor data source, err %d", amresult);
    }
    else {
        LOGE("AMediaExtractor_setDataSource called with: [%s]", s.c_str());
        LOGE("SUCCESS !!! %d", amresult);
    }

    return;
}
