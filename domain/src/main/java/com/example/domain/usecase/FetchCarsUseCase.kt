package com.example.domain.usecase

import com.example.base.MyResponse
import com.example.domain.model.CarDomainModel
import com.example.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCarsUseCase @Inject constructor(private val carRepository: CarRepository) {
    suspend operator fun invoke(): Flow<MyResponse<List<CarDomainModel>>> {
        return carRepository.fetchCars()
    }
}