package com.example.helloworld.eighth_class

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.helloworld.repository.entities.UiModel
import com.example.helloworld.repository.entities.roundedStarCount
import com.example.helloworld.repository.remote.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EighthClassViewModel(private val repository: GithubRepository) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<UiModel>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<UiModel>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<UiModel>> = repository.getSearchResultStream(queryString)
            .map { pagingData -> pagingData.map { UiModel.RepoItem(it) } }
            .map {
                it.insertSeparators { before, after -> insertSeparators(before, after) }
            }
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    private fun insertSeparators(
        before: UiModel.RepoItem?,
        after: UiModel.RepoItem?
    ): UiModel.SeparatorItem? {
        if (after == null) {
            // we're at the end of the list
            return null
        }
        if (before == null) {
            // we're at the beginning of the list
            return validateSeparatorItem(after)
        }
        // check between 2 items
        return if (before.roundedStarCount > after.roundedStarCount) {
            validateSeparatorItem(after)
        } else {
            // no separator
            null
        }
    }

    private fun validateSeparatorItem(after: UiModel.RepoItem) =
        if (after.roundedStarCount >= 1) {
            UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
        } else {
            UiModel.SeparatorItem("< 10.000+ stars")
        }

}