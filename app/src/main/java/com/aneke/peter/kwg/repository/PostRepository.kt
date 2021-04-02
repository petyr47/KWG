package com.aneke.peter.kwg.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aneke.peter.kwg.data.AppDatabase
import com.aneke.peter.kwg.data.Post
import com.aneke.peter.kwg.network.ApiInterface
import com.aneke.peter.kwg.paging.PostSource
import kotlinx.coroutines.flow.Flow

class PostRepository(private val service : ApiInterface, private val db : AppDatabase) {

    private val postDao = db.postDao()


    private suspend fun fetchPosts(pageNumber : Int = 1) : List<Post>{
        try {
            return service.fetchComments(pageNumber)
        } catch (e : Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    suspend fun savePosts() {
        try {
            val posts = fetchPosts()
            postDao.insertPosts(posts)
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    @ExperimentalPagingApi
    fun letPostsFlowFromDb() : Flow<PagingData<Post>> {
        val config = PagingConfig(pageSize = 25, maxSize = 100)

        val pagingSourceFactory = { db.postDao().fetchPagingPosts()}

        return Pager(
            config = config,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = PostSource(service, db)
        ).flow
    }


}