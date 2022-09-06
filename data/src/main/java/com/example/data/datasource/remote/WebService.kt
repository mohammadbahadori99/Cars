package com.example.data.datasource.remote


import com.example.data.datasource.model.MyResponseDTO

interface WebService {
    @retrofit2.http.GET("/codingtask/cars")
    suspend fun fetchCars(): List<MyResponseDTO>
}