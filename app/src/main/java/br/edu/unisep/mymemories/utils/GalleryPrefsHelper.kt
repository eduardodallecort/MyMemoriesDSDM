package br.edu.unisep.mymemories.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object GalleryPrefsHelper {

    private lateinit var preferences: SharedPreferences

    fun initialize(context: Context) {
        preferences = context.getSharedPreferences("gallery-preferences", Context.MODE_PRIVATE)
    }

    fun getNextPictureName(): String {
        val pictureNumber = preferences.getInt("picture-count", 0) + 1

        return "picture_$pictureNumber.jpg"
    }

    fun updatePictureCount() {
        val pictureNumber = preferences.getInt("picture-count", 0) + 1
        preferences.edit {
            putInt("picture-count", pictureNumber)
        }
    }
}