package com.aneke.peter.kwg.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.aneke.peter.kwg.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel(){

    @ExperimentalPagingApi
    val posts = getPostStream()

    @ExperimentalPagingApi
    private fun getPostStream() =
        repository.letPostsFlowFromDb().cachedIn(viewModelScope)


}