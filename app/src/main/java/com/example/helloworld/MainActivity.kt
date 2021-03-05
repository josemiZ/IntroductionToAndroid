package com.example.helloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "COOL TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Activity created")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Activity started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Activity resumed")
    }

    override fun onPause() {
        Log.d(TAG, "Activity paused")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "Activity stopped")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "Activity destroyed")
        super.onDestroy()
    }
}