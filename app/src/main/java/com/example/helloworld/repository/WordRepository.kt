package com.example.helloworld.repository

import androidx.annotation.WorkerThread
import com.example.helloworld.repository.entities.Word
import com.example.helloworld.repository.local.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}