package com.example.helloworld.sixth_class

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.helloworld.util.OUTPUT_PATH
import com.example.helloworld.util.makeStatusNotification
import com.example.helloworld.util.sleep
import java.io.File

class CleanupWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification("Cleaning up old temporary files", applicationContext)
        sleep()

        return try {
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
            if (outputDirectory.exists()) {
                val entries = outputDirectory.listFiles() ?: return Result.success()
                for (entry in entries) {
                    val name = entry.name
                    if (name.isNotEmpty() && name.endsWith(".png")) {
                        val deleted = entry.delete()
                        Log.i("", "Deleted $name - $deleted")
                    }
                }
            }
            Result.success()
        } catch (exception: Exception) {
            Log.e("", "", exception)
            Result.failure()
        }
    }
}