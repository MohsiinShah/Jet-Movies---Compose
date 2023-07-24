package com.ahoy.myapplication.hilt

import com.ahoy.myapplication.repository.GetMoviesRepo
import com.ahoy.myapplication.repository.GetMoviesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun providesMoviesRepo(): GetMoviesRepo {
        return GetMoviesRepoImpl()
    }
}