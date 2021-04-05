package com.example.helloworld.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.eighth_class.EighthClassViewModel
import com.example.helloworld.repository.remote.GithubRepository

class ViewModelFactory(private val repository: GithubRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EighthClassViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EighthClassViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}