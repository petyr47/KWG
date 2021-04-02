package com.aneke.peter.kwg.network

import com.aneke.peter.kwg.data.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("comments")
    suspend fun fetchComments( @Query("page") pageNumber: Int,
                               @Query("limit") limit: Int = 100) : List<Post>

}