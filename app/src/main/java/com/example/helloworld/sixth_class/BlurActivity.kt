package com.example.helloworld.sixth_class

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import com.bumptech.glide.Glide
import com.example.helloworld.R
import com.example.helloworld.databinding.ActivityBlurBinding
import com.example.helloworld.util.KEY_IMAGE_URI

class BlurActivity : AppCompatActivity() {
    private lateinit var viewModel: BlurViewModel
    private lateinit var binding: ActivityBlurBinding

    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(BlurViewModel::class.java)

        val imageUriExtra = intent.getStringExtra(KEY_IMAGE_URI)
        viewModel.setImageUri(imageUriExtra)
        viewModel.imageUri?.let { imageUri ->
            Glide.with(this).load(imageUri).into(binding.imageView)
        }

        binding.goButton.setOnClickListener { viewModel.applyBlur(blurLevel) }
        binding.seeFileButton.setOnClickListener {
            viewModel.outputUri?.let { currentUri ->
                val actionView = Intent(Intent.ACTION_VIEW, currentUri)
                actionView.resolveActivity(packageManager)?.run {
                    startActivity(actionView)
                }
            }
        }
        viewModel.outputWorkInfos.observe(this, ::observeOutputWorkInfo)
        binding.cancelButton.setOnClickListener { viewModel.cancelWork() }
    }

    private fun observeOutputWorkInfo(list: List<WorkInfo>?) {
        if (list.isNullOrEmpty()) {
            return
        }

        // We only care about the one output status.
        // Every continuation has only one worker tagged TAG_OUTPUT
        val workInfo = list[0]
        if (workInfo.state.isFinished) {
            showWorkFinished()

            // Normally this processing, which is not directly related to drawing views on
            // screen would be in the ViewModel. For simplicity we are keeping it here.
            val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

            // If there is an output file show "See File" button
            if (!outputImageUri.isNullOrEmpty()) {
                viewModel.setOutputUri(outputImageUri)
                binding.seeFileButton.visibility = View.VISIBLE
            }
        } else {
            showWorkInProgress()
        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            goButton.visibility = View.GONE
            seeFileButton.visibility = View.GONE
        }
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            cancelButton.visibility = View.GONE
            goButton.visibility = View.VISIBLE
        }
    }

}