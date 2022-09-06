package com.example.data.datasource.remote

import com.example.base.MyResponse
import com.example.data.datasource.model.MyResponseDTO
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val webService: WebService) {
    suspend fun fetchCars(): MyResponse<List<MyResponseDTO>> {
        return try {
            val data = webService.fetchCars()
            MyResponse.Success(data)
        } catch (e: Exception) {
            MyResponse.Error(e)
        }
    }
}