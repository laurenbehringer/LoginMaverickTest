package com.example.loginmavericktest.di

import com.example.loginmavericktest.data.remote.MaverickApi
import com.example.loginmavericktest.domain.repository.MaverickRepository
import com.example.loginmavericktest.domain.usecase.LoginMaverickUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMaverickApi() : MaverickApi {
        return Retrofit.Builder()
            .baseUrl(MaverickApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(maverickRepository: MaverickRepository) : LoginMaverickUseCase {
        return LoginMaverickUseCase(maverickRepository)
    }


}