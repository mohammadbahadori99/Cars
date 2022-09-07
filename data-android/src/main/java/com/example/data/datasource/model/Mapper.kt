package com.example.data.datasource.model

import com.example.data.model.MyResponseDTO
import com.example.domain.model.CarDomainModel


fun MyResponseEntity.toMyResponseDomainModel() =
    CarDomainModel(
        carImageUrl,
        color,
        fuelLevel,
        fuelType,
        group,
        id,
        innerCleanliness,
        latitude,
        licensePlate,
        longitude,
        make,
        modelIdentifier,
        modelName,
        name,
        series,
        transmission
    )

fun CarDomainModel.toMyResponseDTO() =
    MyResponseEntity(
        carImageUrl,
        color,
        fuelLevel,
        fuelType,
        group,
        id,
        innerCleanliness,
        latitude,
        licensePlate,
        longitude,
        make,
        modelIdentifier,
        modelName,
        name,
        series,
        transmission
    )

fun MyResponseDTO.toMyResponseEntity() =
    MyResponseEntity(
        carImageUrl,
        color,
        fuelLevel,
        fuelType,
        group,
        id,
        innerCleanliness,
        latitude,
        licensePlate,
        longitude,
        make,
        modelIdentifier,
        modelName,
        name,
        series,
        transmission
    )

fun MyResponseEntity.toMyResponseDTO() =
    MyResponseDTO(
        carImageUrl,
        color,
        fuelLevel,
        fuelType,
        group,
        id,
        innerCleanliness,
        latitude,
        licensePlate,
        longitude,
        make,
        modelIdentifier,
        modelName,
        name,
        series,
        transmission
    )

fun List<MyResponseEntity>.toCarDomainModel() = this.map { it.toMyResponseDomainModel() }