package com.example.rickandmortyapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val species: String,
    val gender: String,
    val origin: String,
    val status: String,
    val image: String,
    val favorite: Boolean = false,
    val custom: Boolean = false
)