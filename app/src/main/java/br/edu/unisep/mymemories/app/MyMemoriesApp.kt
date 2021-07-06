package br.edu.unisep.mymemories.app

import android.app.Application
import br.edu.unisep.mymemories.di.adapterModule
import br.edu.unisep.mymemories.di.repositoryModule
import br.edu.unisep.mymemories.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyMemoriesApp : Application()  {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                repositoryModule,
                viewModelModule,
                adapterModule
            )
        }
    }

}