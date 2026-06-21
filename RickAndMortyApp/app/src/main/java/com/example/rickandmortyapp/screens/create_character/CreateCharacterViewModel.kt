package com.example.rickandmortyapp.screens.create_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.room.CharacterRoomRepository
import com.example.rickandmortyapp.data.room.RoomCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateCharacterViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<RoomCharacter>>(emptyList())
    val characters = _characters.asStateFlow()

    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = CharacterRoomRepository.getCustomCharacters()
        }
    }

    fun createCharacter(
        name: String,
        status: String,
        species: String,
        gender: String,
        origin: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val character = RoomCharacter(
                name = name,
                status = status,
                species = species,
                gender = gender,
                origin = origin,
                image = "",
                favorite = false,
                custom = true
            )
            val newCharacterId = CharacterRoomRepository.insertCharacter(character)
            if (newCharacterId != -1L) {
                _characters.value += character.copy(id = newCharacterId.toInt())
            }
        }
    }
}