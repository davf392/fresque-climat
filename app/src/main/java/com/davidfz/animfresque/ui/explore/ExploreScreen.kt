package com.davidfz.animfresque.ui.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ExploreScreen(
//    freskNavController: FreskNavController,
//    viewModel: ExploreViewModel,
//    onItemClick: (Int) -> Unit // Handle session item click
) {

    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Text(text = "Lister tous les ateliers publics disponibles")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                placeholder = { Text("Entrez le nom d'une ville") }, // City name placeholder
                modifier = Modifier.weight(1f).padding(8.dp),
//                keyboardOptions = KeyboardType.Text
            )
            IconButton(onClick = { /* Handle map button click */ }) {
                Icon(
                    imageVector = Icons.Filled.Place, // Replace with actual icon resource
                    contentDescription = "Map"
                )
            }
        }

//        HorizontalScrollView(modifier = Modifier.fillMaxWidth()) {
//            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                FilterButton("Filters")
//                FilterButton("Rayon") // Distance
//                FilterButton("Language")
//                FilterButton("En ligne") // Online
//                FilterButton("Catégorie") // Category
//            }
//        }

//        val sessionsList by viewModel.sessions.observeAsState()
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(sessionsList) { session ->
//                SessionListItem(session, onItemClick)
//            }
//        }
    }
}

@Composable
fun FilterButton(text: String) {
    Button(
        onClick = { /* Handle filter button click based on text */ },
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Black
        )
    ) {
        Text(text)
    }
}

@Composable
fun SessionListItem(session: SessionItem, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(session.id.toInt()) }
            .clip(RoundedCornerShape(8.dp))
    ) {
        // ... Content for your session item
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(
//        freskNavController = rememberFreskNavController(),
//        viewModel = TODO(),
//        onItemClick = TODO()
    )
}