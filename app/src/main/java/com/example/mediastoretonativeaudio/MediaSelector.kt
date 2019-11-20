package com.example.mediastoretonativeaudio

import android.content.ContentResolver
import android.provider.MediaStore

data class Songs(
    val id: String,
    val title: String?,
    val path: String?
)

fun getSong(contentResolver : ContentResolver) : MutableList<Songs> {
    val SONGS_PROJECTION = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.DATA
    )

    val cursor = contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        SONGS_PROJECTION,
        null,
        null,
        MediaStore.Audio.Media._ID +
                " ASC LIMIT 100"
    )

    val items: MutableList<Songs> = mutableListOf()
    cursor?.let {
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val s0 = cursor.getString(cursor.getColumnIndex(SONGS_PROJECTION[0]))
                val s1 = cursor.getString(cursor.getColumnIndex(SONGS_PROJECTION[1]))
                val s2 = cursor.getString(cursor.getColumnIndex(SONGS_PROJECTION[2]))
                items.add(Songs(s0, s1, s2))
                cursor.moveToNext()
            }
        }
        cursor.close()
    }
    return items
}