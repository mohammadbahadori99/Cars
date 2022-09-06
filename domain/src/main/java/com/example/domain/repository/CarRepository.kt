package com.example.domain.repository

import com.example.base.MyResponse
import com.example.domain.model.CarDomainModel
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun fetchCars(): Flow<MyResponse<List<CarDomainModel>>>
}