package com.example.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.datasource.local.CarDao
import com.example.data.datasource.model.MyResponseEntity

@Database(entities = [MyResponseEntity::class], version = 1, exportSchema = false)
abstract class CarDataBase: RoomDatabase() {
    abstract fun carDao(): CarDao
}