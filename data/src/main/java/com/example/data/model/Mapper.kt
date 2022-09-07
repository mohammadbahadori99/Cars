package com.example.data.model

import com.example.domain.model.CarDomainModel


fun MyResponseDTO.toMyResponseDomainModel() =
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

fun List<MyResponseDTO>.toCarDomainModel() = this.map { it.toMyResponseDomainModel() }