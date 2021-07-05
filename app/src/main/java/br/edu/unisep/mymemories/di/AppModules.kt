package br.edu.unisep.mymemories.di

import br.edu.unisep.mymemories.repository.MemoryRepository
import br.edu.unisep.mymemories.ui.memory.MemoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { MemoryRepository(get()) }
}

val viewModelModule = module {
    viewModel { MemoryViewModel(get()) }
}