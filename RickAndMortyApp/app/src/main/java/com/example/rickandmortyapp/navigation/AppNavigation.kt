package com.example.rickandmortyapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapp.screens.create_character.CreateCharacterScreen
import com.example.rickandmortyapp.screens.create_character.CreateCharacterViewModel
import com.example.rickandmortyapp.screens.custom_characters.CustomCharacterScreen
import com.example.rickandmortyapp.screens.custom_characters.CustomCharacterViewModel
import com.example.rickandmortyapp.screens.favorite_characters.FavoritesScreen
import com.example.rickandmortyapp.screens.favorite_characters.FavoritesViewModel
import com.example.rickandmortyapp.screens.show_characters.CharacterListScreen
import com.example.rickandmortyapp.screens.show_characters.CharacterListViewModel
import kotlinx.serialization.Serializable

@Serializable
object CharacterList

@Serializable
object Favorites

@Serializable
object CreateCharacter

@Serializable
object CustomCharacters

@Composable
fun AppNavigation(
    characterListViewModel: CharacterListViewModel,
    favoritesViewModel: FavoritesViewModel,
    createCharacterViewModel: CreateCharacterViewModel,
    customCharacterViewModel: CustomCharacterViewModel
){
    val navController = rememberNavController()

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedItemIndex == 0,
                    onClick = {
                        selectedItemIndex = 0
                        navController.navigate(CharacterList)
                    },
                    icon = {
                        if (selectedItemIndex == 0) {
                            Icon(
                                imageVector = Icons.Filled.Face,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Face,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Characters")
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 1,
                    onClick = {
                        selectedItemIndex = 1
                        navController.navigate(Favorites)
                    },
                    icon = {
                        if (selectedItemIndex == 1) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Favorites")
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 2,
                    onClick = {
                        selectedItemIndex = 2
                        navController.navigate(CreateCharacter)
                    },
                    icon = {
                        if (selectedItemIndex == 2) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.AddCircle,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Create")
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 3,
                    onClick = {
                        selectedItemIndex = 3
                        navController.navigate(CustomCharacters)
                    },
                    icon = {
                        if (selectedItemIndex == 3) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Custom")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = CharacterList
            ) {
                composable<CharacterList> {
                    CharacterListScreen(characterListViewModel)
                }
                composable<Favorites> {
                    FavoritesScreen(favoritesViewModel)
                }
                composable<CreateCharacter> {
                    CreateCharacterScreen(createCharacterViewModel)
                }
                composable<CustomCharacters> {
                    CustomCharacterScreen(customCharacterViewModel)
                }
            }
        }
    }
}