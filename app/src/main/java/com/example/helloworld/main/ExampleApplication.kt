package com.example.helloworld.main

import android.app.Application
import androidx.work.Configuration
import com.example.helloworld.BuildConfig

class ExampleApplication : Application(), Configuration.Provider {
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