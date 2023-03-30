package com.example.loginmavericktest.di

import com.example.loginmavericktest.data.repository.MaverickRepositoryimpl
import com.example.loginmavericktest.domain.repository.MaverickRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMaverickRepository(
        maverickRepositoryImpl: MaverickRepositoryimpl
    ): MaverickRepository
}