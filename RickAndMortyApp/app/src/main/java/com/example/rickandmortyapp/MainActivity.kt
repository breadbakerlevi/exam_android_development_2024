package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.rickandmortyapp.data.room.CharacterRoomRepository
import com.example.rickandmortyapp.navigation.AppNavigation
import com.example.rickandmortyapp.screens.create_character.CreateCharacterViewModel
import com.example.rickandmortyapp.screens.custom_characters.CustomCharacterViewModel
import com.example.rickandmortyapp.screens.favorite_characters.FavoritesViewModel
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.example.rickandmortyapp.screens.show_characters.CharacterListViewModel

class MainActivity : ComponentActivity() {

    private val _characterListViewModel : CharacterListViewModel by viewModels()
    private val _favoritesViewModel : FavoritesViewModel by viewModels()
    private val _createCharacterViewModel: CreateCharacterViewModel by viewModels()
    private val _customCharacterViewModel: CustomCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CharacterRoomRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                AppNavigation(
                    _characterListViewModel,
                    _favoritesViewModel,
                    _createCharacterViewModel,
                    _customCharacterViewModel
                )
            }
        }
    }
}

