package com.example.mediastoretonativeaudio

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionRequest()

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString() + "/" + getSong(
            contentResolver
        ).get(0).id

        Log.d("MediaStoreToNativeAudio", "uri[" + uri + "]")

        val URI = Uri.parse( uri )
        val pfd = contentResolver.openFileDescriptor(URI, "r")
        parcelFileDescToJNI(pfd?.fd!!, 0, pfd.statSize)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun parcelFileDescToJNI(fd : Int, offset : Long, length : Long)

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    fun permissionRequest() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                707
            )
    }
}

