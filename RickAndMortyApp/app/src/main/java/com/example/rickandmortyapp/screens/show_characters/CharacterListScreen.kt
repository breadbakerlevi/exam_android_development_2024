package com.example.rickandmortyapp.screens.show_characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.CharacterItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun CharacterListScreen(characterListViewModel: CharacterListViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        characterListViewModel.fetchCharacters()
    }

    val characters by characterListViewModel.characters.collectAsState()
    val info by characterListViewModel.info.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Rick & Morty - Characters",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (info != null) {
                Text(
                    text = "Total Characters: ${info?.count}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text =  "Total Pages: ${info?.pages}",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(characters) { character ->
                    CharacterItem(
                        character = character,
                        onButtonClick = { characterListViewModel.toggleFavorite(it) },
                        buttonIcon = Icons.Filled.Add,
                        buttonDescription = "Add ${character.name} to favorites"
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
            ) {
                Button(
                    onClick = { characterListViewModel.fetchPreviousPage() },
                    enabled = info?.prev != null
                ) {
                    Text("Previous")
                }

                Button(
                    onClick = { characterListViewModel.fetchNextPage() },
                    enabled = info?.next != null
                ) {
                    Text("Next")
                }
            }
        }
    }
}