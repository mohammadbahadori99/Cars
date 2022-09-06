package com.example.data.datasource.local

import com.example.data.datasource.model.MyResponseDTO
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dao: CarDao) {
    suspend fun getAllCars(): List<MyResponseDTO> {
        return dao.getAllCars()

    }

    suspend fun insertAllCars(carList: List<MyResponseDTO>) {
        dao.insertAllCars(carList)
    }
}