package com.example.data.datasource.di

import com.example.data.datasource.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://cdn.sixt.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService =
        retrofit.create(WebService::class.java)

}