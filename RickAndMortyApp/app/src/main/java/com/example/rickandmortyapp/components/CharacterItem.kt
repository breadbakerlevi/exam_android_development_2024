package com.example.rickandmortyapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyapp.data.data_classes.Character

@Composable
fun CharacterItem(
    character: Character,
    onButtonClick: (Character) -> Unit,
    buttonIcon: ImageVector,
    buttonDescription: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = character.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Status: ${character.status}",
                fontSize = 14.sp,
            )
            Text(
                text = "Species: ${character.species}",
                fontSize = 14.sp,
            )
            Text(
                text = "Gender: ${character.gender}",
                fontSize = 14.sp,
            )
            Text(
                text = "Origin: ${character.origin.name}",
                fontSize = 14.sp,
            )
        }

        Button(
            onClick = { onButtonClick(character) },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                imageVector = buttonIcon,
                contentDescription = buttonDescription
            )
        }
    }
}