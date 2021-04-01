package com.example.helloworld.sixth_class

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.helloworld.util.*

class BlurWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Blurring image", applicationContext)

        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        (0..100 step 10).forEach {
            setProgressAsync(workDataOf(PROGRESS to it))
            sleep()
        }

        return try {
            if (resourceUri.isNullOrEmpty()) {
                Log.e("", "Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }

            val resolver = applicationContext.contentResolver

            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceUri)))

            val output = blurBitmap(picture, applicationContext)

            // Write bitmap to a temp file
            val outputUri = writeBitmapToFile(applicationContext, output)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())

            Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e("", "Error applying blur", throwable)
            Result.failure()
        }
    }
}