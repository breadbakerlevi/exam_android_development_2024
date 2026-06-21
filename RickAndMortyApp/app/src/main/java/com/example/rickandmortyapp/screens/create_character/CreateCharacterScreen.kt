package com.example.rickandmortyapp.screens.create_character

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateCharacterScreen(createCharacterViewModel: CreateCharacterViewModel) {
    val characters = createCharacterViewModel.characters.collectAsState()

    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var origin by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        createCharacterViewModel.setCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Create New Character",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Total Custom Characters: ${characters.value.count()}")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = species,
            onValueChange = { species = it },
            label = { Text("Species") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = origin,
            onValueChange = { origin = it },
            label = { Text("Origin") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                createCharacterViewModel.createCharacter(
                    name = name,
                    status = status,
                    species = species,
                    gender = gender,
                    origin = origin
                )

                name = ""
                status = ""
                species = ""
                gender = ""
                origin = ""
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank() && status.isNotBlank() &&
                    species.isNotBlank() && gender.isNotBlank() &&
                    origin.isNotBlank()
        ) {
            Text("Create Character")
        }
    }
}