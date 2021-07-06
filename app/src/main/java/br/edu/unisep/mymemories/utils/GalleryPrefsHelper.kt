package br.edu.unisep.mymemories.utils

import android.content.Context
import android.content.SharedPreferences

object GalleryPrefsHelper {

    private lateinit var preferences: SharedPreferences

    fun initialize(context: Context) {
        preferences = context.getSharedPreferences("gallery-preferences", Context.MODE_PRIVATE)
    }

}