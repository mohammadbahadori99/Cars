package com.example.data

import com.example.data.model.MyResponseDTO


interface LocalDataSource {
    suspend fun getAllCars(): List<MyResponseDTO>

    suspend fun insertAllCars(carList: List<MyResponseDTO>)
}