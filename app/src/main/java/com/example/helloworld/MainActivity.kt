package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "COOL TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_go_to_next).apply {
            setOnClickListener {
                openFourthClass()
            }
        }
        Log.d(TAG, "Activity created")
    }

    private fun openThirdClass() {
        openActivity<ThirdClassActivity>()
    }

    private fun openFourthClass() {
        openActivity<FourthClassActivity>()
    }

    private inline fun <reified T> openActivity() {
        Intent(this, T::class.java).also {
            startActivity(it)
        }
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