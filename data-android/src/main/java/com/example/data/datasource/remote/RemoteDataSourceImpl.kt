package com.example.data.datasource.remote

import com.example.base.MyResponse
import com.example.data.datasource.model.toMyResponseDTO
import com.example.data.model.MyResponseDTO
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val webService: WebService) :
    com.example.data.RemoteDataSource {
    override suspend fun fetchCars(): MyResponse<List<MyResponseDTO>> {
        return try {
            val data = webService.fetchCars()
            MyResponse.Success(data.map { it.toMyResponseDTO() })
        } catch (e: Exception) {
            MyResponse.Error(e)
        }
    }
}