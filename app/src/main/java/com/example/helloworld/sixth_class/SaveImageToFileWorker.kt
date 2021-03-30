package com.example.helloworld.sixth_class

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.helloworld.util.KEY_IMAGE_URI
import com.example.helloworld.util.makeStatusNotification
import com.example.helloworld.util.sleep
import java.text.SimpleDateFormat
import java.util.*

class SaveImageToFileWorker(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {

    private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    override fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification("Saving image", applicationContext)
        sleep()

        val resolver = applicationContext.contentResolver
        return try {
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            val bitmap = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceUri))
            )
            val imageUrl = MediaStore.Images.Media.insertImage(
                resolver, bitmap, title, dateFormatter.format(Date())
            )
            if (!imageUrl.isNullOrEmpty()) {
                val output = workDataOf(KEY_IMAGE_URI to imageUrl)

                Result.success(output)
            } else {
                Log.e("", "Writing to MediaStore failed")
                Result.failure()
            }
        } catch (exception: Exception) {
            Log.e("", "", exception)
            Result.failure()
        }
    }

}