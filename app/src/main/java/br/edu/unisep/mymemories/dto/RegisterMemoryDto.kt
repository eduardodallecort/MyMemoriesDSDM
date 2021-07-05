package br.edu.unisep.mymemories.dto

data class RegisterMemoryDto(
    val picture: String,
    val city: String,
    val memoryDate: String,
    val description: String,
    val localeLatitude: String,
    val localeLongitude: String
)
