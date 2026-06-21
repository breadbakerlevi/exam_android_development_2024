package com.example.rickandmortyapp.screens.show_characters

import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.data_classes.Character
import com.example.rickandmortyapp.data.services.CharacterApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.data_classes.Info
import com.example.rickandmortyapp.data.room.CharacterRoomRepository
import com.example.rickandmortyapp.data.room.RoomCharacter
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.asStateFlow

class CharacterListViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _info = MutableStateFlow<Info?>(null)
    val info = _info.asStateFlow()

    fun fetchCharacters(pageUrl: String? = null) {
        viewModelScope.launch {
            try {
                val characterList = CharacterApiRepository.getAllCharacters(pageUrl ?: "")

                _characters.value = characterList.results.toList() //Chat
                _info.value = characterList.info
            } catch (e: Exception) {
                println("Error fetching characters: ${e.message}")
            }
        }
    }

    fun toggleFavorite(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            val roomCharacter = RoomCharacter(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                gender = character.gender,
                origin = character.origin.name,
                image = character.image,
                favorite = true,
                custom = false
            )
            CharacterRoomRepository.insertCharacter(roomCharacter)
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            try {
                val nextPage = info.value?.next
                if (nextPage != null) {
                    fetchCharacters(nextPage)
                }
            } catch (e: Exception) {
                println("Error fetching characters: ${e.message}")
            }
        }
    }

    fun fetchPreviousPage() {
        viewModelScope.launch {
            val prevPage = info.value?.prev
            if (prevPage != null) {
                fetchCharacters(prevPage)
            }
        }
    }
}


