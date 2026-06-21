
package com.example.rickandmortyapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomCharacter::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}