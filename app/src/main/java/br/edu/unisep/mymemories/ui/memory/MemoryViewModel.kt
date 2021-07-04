package br.edu.unisep.mymemories.ui.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Memory Fragment"
    }
    val text: LiveData<String> = _text
}