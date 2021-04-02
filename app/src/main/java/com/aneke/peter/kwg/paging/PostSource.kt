package com.aneke.peter.kwg.paging

import androidx.paging.*
import androidx.room.withTransaction
import com.aneke.peter.kwg.data.AppDatabase
import com.aneke.peter.kwg.data.Post
import com.aneke.peter.kwg.data.RemoteKeys
import com.aneke.peter.kwg.network.ApiInterface
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import java.util.*

@ExperimentalPagingApi
class PostSource(private val service : ApiInterface, private val db: AppDatabase) : RemoteMediator<Int, Post>() {

    private val DEFAULT_PAGE_INDEX = 1
    private val PAGE_SIZE = 25
    private val MAX_PAGE_INDEX = 3
    private val MAX_PAGES = 100

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Post>): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when(pageKeyData){
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val posts = service.fetchComments(pageNumber = page, limit = PAGE_SIZE)
            val isEndOfList = posts.isEmpty() || page == MAX_PAGE_INDEX || page >= MAX_PAGES
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeys()
                    db.postDao().clearPostTable()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page -1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = posts.map {
                    RemoteKeys(repoId = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                db.remoteKeysDao().insertAll(keys)
                db.postDao().insertPosts(posts)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e : IOException){
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }



    private suspend fun getFirstRemoteKey(state : PagingState<Int, Post>) : RemoteKeys? {
        return state.pages
            .firstOrNull{it.data.isNotEmpty()}
            ?.data?.firstOrNull()
            ?.let { post -> db.remoteKeysDao().remoteKeysPost(post.id.toString()) }
    }

    private suspend fun getLastRemoteKey(state : PagingState<Int, Post>) : RemoteKeys? {
        return state.pages
            .lastOrNull{it.data.isNotEmpty()}
            ?.data?.lastOrNull()
            ?.let { post -> db.remoteKeysDao().remoteKeysPost(post.id.toString()) }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, Post>) : RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                db.remoteKeysDao().remoteKeysPost(repoId.toString())
            }
        }
    }


    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, Post>) : Any? {
        return when(loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }




}