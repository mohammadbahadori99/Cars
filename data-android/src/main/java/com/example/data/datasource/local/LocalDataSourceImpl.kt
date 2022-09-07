package com.example.data.datasource.local

import com.example.data.datasource.model.toMyResponseDTO
import com.example.data.datasource.model.toMyResponseEntity
import com.example.data.model.MyResponseDTO
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: CarDao) : com.example.data.LocalDataSource {
    override suspend fun getAllCars(): List<MyResponseDTO> {
        return dao.getAllCars().map { it.toMyResponseDTO() }

    }

    override suspend fun insertAllCars(carList: List<MyResponseDTO>) {
        dao.insertAllCars(carList.map { it.toMyResponseEntity() })
    }
}