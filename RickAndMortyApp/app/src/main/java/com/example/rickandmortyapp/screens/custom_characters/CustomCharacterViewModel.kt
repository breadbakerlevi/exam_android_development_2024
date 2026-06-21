package com.example.rickandmortyapp.screens.custom_characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.room.CharacterRoomRepository
import com.example.rickandmortyapp.data.room.RoomCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomCharacterViewModel : ViewModel() {
    private val _customCharacters = MutableStateFlow<List<RoomCharacter>>(emptyList())
    val customCharacters: StateFlow<List<RoomCharacter>> = _customCharacters

    fun fetchCustomCharacters() {
        viewModelScope.launch {
            _customCharacters.value = CharacterRoomRepository.getCustomCharacters()
        }
    }

    fun deleteCharacter(character: RoomCharacter) {
        viewModelScope.launch {
            CharacterRoomRepository.deleteCharacter(character)
            fetchCustomCharacters()
        }
    }
}