package com.example.rickandmortyapp.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import java.sql.SQLException

object CharacterRoomRepository {
    private lateinit var _characterDatabase: CharacterDatabase
    private val _characterDao by lazy { _characterDatabase.characterDao() }

    fun initializeDatabase(context: Context) {
        _characterDatabase = Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            name = "character-database"
        ).build()
    }

    suspend fun getFavoriteCharacters(): List<RoomCharacter> {
        try {
            return _characterDao.getFavoriteCharacters()
        } catch (e: SQLException) {
            Log.e("Database error", e.toString())
            return emptyList()
        }
    }

    suspend fun getCustomCharacters(): List<RoomCharacter> {
        try {
            return _characterDao.getCustomCharacters()
        } catch (e: SQLException) {
            Log.e("Database error", e.toString())
            return emptyList()
        }
    }

    suspend fun insertCharacter(character: RoomCharacter): Long {
        try {
            return _characterDao.insertCharacter(character)
        } catch (e: SQLException) {
            Log.e("Database error", e.toString())
            return -1L
        }
    }

    suspend fun deleteCharacter(character: RoomCharacter): Int {
        try {
            return _characterDao.deleteCharacter(character)
        } catch (e: SQLException) {
            Log.e("Database error", e.toString())
            return 0
        }
    }
}