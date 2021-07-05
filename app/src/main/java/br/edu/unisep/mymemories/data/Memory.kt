package br.edu.unisep.mymemories.data

data class Memory(
    var id: Int? = null,
    val picture: String,
    val city: String,
    val memoryDate: String,
    val description: String,
    val localeLatitude: String,
    val localeLongitude: String
)