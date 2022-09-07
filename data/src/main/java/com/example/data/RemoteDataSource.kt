package com.example.data

import com.example.base.MyResponse
import com.example.data.model.MyResponseDTO

interface RemoteDataSource {
    suspend fun fetchCars(): MyResponse<List<MyResponseDTO>>
}