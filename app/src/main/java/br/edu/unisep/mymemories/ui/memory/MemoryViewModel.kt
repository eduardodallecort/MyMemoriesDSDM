package br.edu.unisep.mymemories.ui.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.unisep.mymemories.dto.MemoryDto
import br.edu.unisep.mymemories.repository.MemoryRepository
import kotlinx.coroutines.launch

class MemoryViewModel(private val repository: MemoryRepository) : ViewModel() {

    private val mMemoriesResult: MutableLiveData<List<MemoryDto>> = MutableLiveData()

    val memoriesResult: LiveData<List<MemoryDto>>
        get() = mMemoriesResult

    private val mMemoriesError: MutableLiveData<Unit> = MutableLiveData()
    val memoriesError: LiveData<Unit>
        get() = mMemoriesError

    fun listMemories() {
        repository.findAll(
            { result -> mMemoriesResult.postValue(result) },
            { mMemoriesError.postValue(Unit) }
        )
    }

}