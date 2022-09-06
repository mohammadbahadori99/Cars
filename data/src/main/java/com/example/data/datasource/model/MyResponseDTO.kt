package com.example.data.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "cars")
@Serializable
data class MyResponseDTO(
    val carImageUrl: String,
    val color: String,
    val fuelLevel: Double,
    val fuelType: String,
    val group: String,
    @PrimaryKey
    val id: String,
    val innerCleanliness: String,
    val latitude: Double,
    val licensePlate: String,
    val longitude: Double,
    val make: String,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val series: String,
    val transmission: String
)