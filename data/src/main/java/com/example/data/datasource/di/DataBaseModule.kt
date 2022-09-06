package com.example.data.datasource.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.CarDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            CarDataBase::class.java,
            "CarDataBase"
        )
            .build()

    @Singleton
    @Provides
    fun provideDao(database: CarDataBase) = database.carDao()
}