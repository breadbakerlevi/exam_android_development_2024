package com.example.rickandmortyapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM RoomCharacter WHERE favorite = 1")
    suspend fun getFavoriteCharacters(): List<RoomCharacter>

    @Query("SELECT * FROM RoomCharacter WHERE custom = 1")
    suspend fun getCustomCharacters(): List<RoomCharacter>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: RoomCharacter): Long

    @Delete
    suspend fun deleteCharacter(character: RoomCharacter): Int
}