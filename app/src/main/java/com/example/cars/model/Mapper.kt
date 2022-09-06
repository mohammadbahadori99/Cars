package com.example.cars.model

import com.example.domain.model.CarDomainModel

fun CarView.toCarDomainModel() = CarDomainModel(
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

fun CarDomainModel.toCarView() = CarView(
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