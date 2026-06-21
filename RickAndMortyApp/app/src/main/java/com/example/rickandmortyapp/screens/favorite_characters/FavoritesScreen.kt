package com.example.rickandmortyapp.screens.favorite_characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyapp.components.CharacterItem
import com.example.rickandmortyapp.data.data_classes.Character
import com.example.rickandmortyapp.data.data_classes.Origin

@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel = viewModel()) {
    val favoriteCharacters by favoritesViewModel.favoriteCharacters.collectAsState()

    LaunchedEffect(Unit) {
        favoritesViewModel.fetchFavoriteCharacters()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Favorite Characters",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favoriteCharacters.isEmpty()) {
            Text("No favorite characters found.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteCharacters) { roomCharacter ->
                    CharacterItem(
                        character = Character(
                            id = roomCharacter.id,
                            name = roomCharacter.name,
                            status = roomCharacter.status,
                            species = roomCharacter.species,
                            gender = roomCharacter.gender,
                            origin = Origin(name = roomCharacter.origin),
                            image = roomCharacter.image
                        ),
                        onButtonClick = { favoritesViewModel.toggleFavorite(roomCharacter) },
                        buttonIcon = Icons.Filled.Delete,
                        buttonDescription = "Remove ${roomCharacter.name} from favorites"
                    )
                }
            }
        }
    }
}