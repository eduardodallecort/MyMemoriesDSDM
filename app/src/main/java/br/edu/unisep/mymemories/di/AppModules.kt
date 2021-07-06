package br.edu.unisep.mymemories.di

import br.edu.unisep.mymemories.repository.MemoryRepository
import br.edu.unisep.mymemories.ui.memory.MemoryAdapter
import br.edu.unisep.mymemories.ui.memory.MemoryViewModel
import br.edu.unisep.mymemories.ui.newmemory.NewMemoryViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { MemoryRepository(Firebase.firestore) }
}

val viewModelModule = module {
    viewModel { MemoryViewModel(get()) }
    viewModel { NewMemoryViewModel(get()) }
}

val adapterModule = module {
    factory { MemoryAdapter() }
}