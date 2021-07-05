package br.edu.unisep.mymemories.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun getUriForFile(context: Context, file: File): Uri {
    return FileProvider.getUriForFile(context,
    "br.edu.unisep.mymemories.fileprovider", file)
}