package com.example.rickandmortyapp.data.data_classes


data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val status: String,
    val image: String,
)

data class Origin (
    val name: String
)