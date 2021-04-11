package com.example.helloworld.main

import android.app.Application
import androidx.work.Configuration
import com.example.helloworld.BuildConfig
import com.example.helloworld.repository.WordRepository
import com.example.helloworld.repository.local.RepoDatabase
import com.example.helloworld.util.Injection

class ExampleApplication : Application(), Configuration.Provider {

    val database by lazy { RepoDatabase.getInstance(this, Injection.applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }

    override fun getWorkManagerConfiguration(): Configuration {
        return if (BuildConfig.DEBUG) {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .build()
        } else {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.ERROR)
                .build()
        }
    }
}