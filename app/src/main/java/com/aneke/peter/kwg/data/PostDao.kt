package com.aneke.peter.kwg.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST

@Dao
interface PostDao {

    @Insert
    suspend fun insertPost(post: Post)

    @Insert
    suspend fun insertPosts(post: List<Post>)

    @Query("DELETE FROM post_table")
    suspend fun clearPostTable()

    @Query("SELECT * FROM post_table")
    suspend fun fetchPosts() : List<Post>

    @Query("SELECT * FROM post_table")
    fun observePosts() : LiveData<List<Post>>

    @Query("SELECT * FROM post_table")
    fun letPostsFlow() : Flow<List<Post>>

    @Query("SELECT * FROM post_table")
    fun fetchPagingPosts() : PagingSource<Int, Post>

}