package br.edu.unisep.mymemories.ui.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.unisep.mymemories.dto.MemoryDto
import br.edu.unisep.mymemories.repository.MemoryRepository
import kotlinx.coroutines.launch

class MemoryViewModel(private val memoryRepository: MemoryRepository) : ViewModel() {


    val memoriesResult: MutableLiveData<List<MemoryDto>> = MutableLiveData()

    fun getMemories() {
        viewModelScope.launch {
            val result = memoryRepository.getList()
            memoriesResult.postValue(result)
        }
    }

}