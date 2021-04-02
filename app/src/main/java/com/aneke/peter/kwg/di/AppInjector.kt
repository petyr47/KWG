package com.aneke.peter.kwg.di

import com.aneke.peter.kwg.posts.PostViewModel
import com.aneke.peter.kwg.data.AppDatabase
import com.aneke.peter.kwg.network.RetrofitClient
import com.aneke.peter.kwg.repository.PostRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { PostRepository(get(), get()) }
}

val dataModule = module {
    single { RetrofitClient.makeApiService()}
    single { AppDatabase.getInstance(get()) }
}

val viewModelModule = module {
    viewModel { PostViewModel(get()) }
}

val modules = listOf(repositoryModule, dataModule, viewModelModule)