package br.edu.unisep.mymemories.dto

data class MemoryDto(
    var id: Int? = null,
    val picture: String,
    val city: String,
    val memoryDate: String,
    val description: String,
    val localeLatitude: String,
    val localeLongitude: String
)
