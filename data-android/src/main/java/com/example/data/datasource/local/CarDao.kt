package com.example.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datasource.model.MyResponseEntity


@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCars(list: List<MyResponseEntity>)

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<MyResponseEntity>

}