package ru.storeva.app.domain.models

data class CityModel(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val subCities: List<CityModel>,
)