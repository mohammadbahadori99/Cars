package com.example.data.datasource.di

import com.example.data.datasource.local.LocalDataSourceImpl
import com.example.data.datasource.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): com.example.data.LocalDataSource

    @Binds
    @Singleton
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): com.example.data.RemoteDataSource
}