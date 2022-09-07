package com.example.data.repositry

import com.example.base.MyResponse
import com.example.data.LocalDataSource
import com.example.data.RemoteDataSource
import com.example.data.model.MyResponseDTO
import com.example.data.model.toCarDomainModel
import com.example.domain.model.CarDomainModel
import com.example.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : CarRepository {
    override suspend fun fetchCars(): Flow<MyResponse<List<CarDomainModel>>> {
        val emptyResponse = emptyFlow<MyResponse<List<CarDomainModel>>>()
        val localData = localDataSource.getAllCars()
        return if (localData.isNotEmpty()) {
            flowOf(MyResponse.Success(localData.toCarDomainModel()))
        } else {
            handleRemoteData(remoteDataSource.fetchCars()) ?: emptyResponse
        }
    }

    private suspend fun handleRemoteData(remoteData: MyResponse<List<MyResponseDTO>>): Flow<MyResponse<List<CarDomainModel>>>? {
        return when (remoteData) {
            is MyResponse.Success -> {
                localDataSource.insertAllCars(remoteData.data)
                flowOf(
                    MyResponse.Success(
                        data = localDataSource.getAllCars().toCarDomainModel()
                    )
                )
            }
            is MyResponse.Error -> {
                flowOf(MyResponse.Error(remoteData.error))
            }
            else -> {
                null
            }
        }
    }
}