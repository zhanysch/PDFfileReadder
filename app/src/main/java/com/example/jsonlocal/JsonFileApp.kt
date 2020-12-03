package com.example.jsonlocal

import android.app.Application
import android.content.Context
import com.example.jsonlocal.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContext
import org.koin.core.context.startKoin

class JsonFileApp : Application() {



    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidContext(this@JsonFileApp)
            modules(appModules)
        }
    }

    companion object {     // context создан для того чтобы смогли к нему обратиться с любого класа
                            // т.к object FileUtils требует Context!!!!
        private lateinit var context: Context
         fun applicationContext() = context
    }
}