package com.example.rickandmortyapp.screens.custom_characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyapp.components.CharacterItem
import com.example.rickandmortyapp.data.data_classes.Character
import com.example.rickandmortyapp.data.data_classes.Origin

@Composable
fun CustomCharacterScreen(customCharacterViewModel: CustomCharacterViewModel = viewModel()) {
    val customCharacters by customCharacterViewModel.customCharacters.collectAsState()

    LaunchedEffect(Unit) {
        customCharacterViewModel.fetchCustomCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Custom Characters",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (customCharacters.isEmpty()) {
            Text("No custom characters found.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(customCharacters) { roomCharacter ->
                    CharacterItem(
                        character = Character(
                            id = roomCharacter.id,
                            name = roomCharacter.name,
                            status = roomCharacter.status,
                            species = roomCharacter.species,
                            gender = roomCharacter.gender,
                            origin = Origin(name = roomCharacter.origin),
                            image = ""
                        ),
                        onButtonClick = { customCharacterViewModel.deleteCharacter(roomCharacter) },
                        buttonIcon = Icons.Filled.Delete,
                        buttonDescription = "Delete custom character"
                    )
                }
            }
        }
    }
}