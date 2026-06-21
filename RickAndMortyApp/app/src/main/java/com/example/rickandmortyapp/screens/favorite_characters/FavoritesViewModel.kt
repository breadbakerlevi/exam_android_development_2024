package com.example.rickandmortyapp.screens.favorite_characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.rickandmortyapp.data.room.RoomCharacter
import com.example.rickandmortyapp.data.room.CharacterRoomRepository
import kotlinx.coroutines.Dispatchers

class FavoritesViewModel : ViewModel() {
    private val _favoriteCharacters = MutableStateFlow<List<RoomCharacter>>(emptyList())
    val favoriteCharacters: StateFlow<List<RoomCharacter>> = _favoriteCharacters

    private val characterRepository = CharacterRoomRepository

    fun fetchFavoriteCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val favorites = characterRepository.getFavoriteCharacters()
                _favoriteCharacters.value = favorites
            } catch (e: Exception) {
                Log.e("FavoritesViewModel", "Error fetching favorite characters: ${e.message}")
            }
        }
    }

    fun toggleFavorite(character: RoomCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                characterRepository.deleteCharacter(character)
                fetchFavoriteCharacters()
            } catch (e: Exception) {
                Log.e("FavoritesViewModel", "Error removing favorite: ${e.message}")
            }
        }
    }
}