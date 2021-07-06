package br.edu.unisep.mymemories.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File

fun getUriForFile(context: Context, file: File): Uri {
    return FileProvider.getUriForFile(context,
    "br.edu.unisep.mymemories.fileprovider", file)
}

fun bitmapToBase64(bitmap: Bitmap): String {

    val byteArrayOutputStream = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

    val imageArray = byteArrayOutputStream.toByteArray()

    val imageBase64 = Base64.encodeToString(imageArray, Base64.NO_WRAP)

    return imageBase64
}