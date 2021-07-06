package br.edu.unisep.mymemories.repository

import android.content.Context
import android.os.Environment
import br.edu.unisep.mymemories.data.Memory
import br.edu.unisep.mymemories.dto.MemoryDto
import br.edu.unisep.mymemories.dto.RegisterMemoryDto
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.File

class MemoryRepository(private val firestore: FirebaseFirestore) {

    fun save(memoryDto: RegisterMemoryDto, onSuccess: () -> Unit, onFailure: () -> Unit) {
        firestore.collection(MEMORIES_COLLECTION)
            .add(memoryDto)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    fun findAll(onSuccess: (List<MemoryDto>) -> Unit,onFailure: () -> Unit) {
        firestore.collection(MEMORIES_COLLECTION)
            .get()
            .addOnSuccessListener { memories ->
                val result = memories.map { memory ->
                    MemoryDto(
                        memory.id,
                        memory.getString(FIELD_PICTURE) ?: "",
                        memory.getString(FIELD_CITY) ?: "",
                        memory.getString(FIELD_MEMORY_DATE) ?: "",
                        memory.getString(FIELD_DESCRIPTION) ?: "",
                        memory.getString(FIELD_LOCALE_LATITUDE) ?: "",
                        memory.getString(FIELD_LOCALE_LONGITUDE) ?: ""
                    )
                }

                onSuccess(result)
            }
            .addOnFailureListener { onFailure() }
    }

    fun getList(): List<MemoryDto>  {
        return emptyList()
    }

    fun getImages(context: Context): Array<File> {
        val images = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return images?.listFiles() ?: arrayOf()
    }


    companion object {
        private const val MEMORIES_COLLECTION = "memories"

        private const val FIELD_CITY = "city"

        private const val FIELD_DESCRIPTION = "description"

        private const val FIELD_LOCALE_LATITUDE = "localeLatitude"

        private const val FIELD_LOCALE_LONGITUDE = "localeLongitude"

        private const val FIELD_MEMORY_DATE = "memoryDate"

        private const val FIELD_PICTURE = "picture"
    }
}
