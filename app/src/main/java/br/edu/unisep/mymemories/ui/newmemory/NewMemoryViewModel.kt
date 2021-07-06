package br.edu.unisep.mymemories.ui.newmemory

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.unisep.mymemories.dto.RegisterMemoryDto
import br.edu.unisep.mymemories.repository.MemoryRepository
import java.io.File
import java.time.LocalDate

class NewMemoryViewModel(private val repository: MemoryRepository) : ViewModel()  {

    private val mOnSaveSuccess: MutableLiveData<Unit> = MutableLiveData()
    val onSaveSuccess: LiveData<Unit>
        get() = mOnSaveSuccess

    private val mOnSaveError: MutableLiveData<Unit> = MutableLiveData()
    val onSaveError: LiveData<Unit>
        get() = mOnSaveError

    val images: MutableLiveData<Array<File>> = MutableLiveData()

    fun save(picture: String, city: String, description: String, localeLatitude: String, localeLongitude: String) {
        val memory = RegisterMemoryDto(picture, city, LocalDate.now().toString(), description, localeLatitude, localeLongitude)

        repository.save(memory,
            { mOnSaveSuccess.postValue(Unit) },
            { mOnSaveError.postValue(Unit) }
        )
    }

    fun getImages(context: Context) {
        images.postValue(repository.getImages(context))
    }

}