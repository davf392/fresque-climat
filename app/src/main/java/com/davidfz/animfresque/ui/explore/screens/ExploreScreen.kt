package com.davidfz.animfresque.ui.explore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidfz.animfresque.ui.explore.components.WorkshopItem
import com.davidfz.animfresque.ui.explore.viewmodels.ExploreViewModel
import com.davidfz.animfresque.ui.profile.components.FilterButton
import com.davidfz.animfresque.ui.theme.FresqueClimatColors


@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: ExploreViewModel = viewModel()
//    onItemClick: (Int) -> Unit // Handle session item click
) {
    val state by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            // Search Bar
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Rechercher") },
                    placeholder = { Text("Entrez le nom d'une ville") },
                    modifier = Modifier.weight(1f).padding(start = 4.dp),
                    trailingIcon = {
                        if (searchQuery.text.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = TextFieldValue("") }) {
                                Icon(Icons.Filled.Clear, contentDescription = "Effacer")
                            }
                        }
                    },
                    singleLine = true
                )

                IconButton(onClick = { /* TODO: handle map button click */ }, modifier = Modifier.weight(.2f)) {
                    Icon(
                        imageVector = Icons.Filled.Map,
                        contentDescription = "Carte",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            // Filters
            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.filtersList.size) { index ->
                    FilterButton(state.filtersList[index])
                }
            }

            // Workshops list
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                items(state.workshopList.size) { index ->
                    WorkshopItem(
                        workshop = state.workshopList[index],
                        onClickWorkShopItem = { viewModel.onWorkshopItemClick(state.workshopList[index]) }
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = { /* TODO: Handle FAB click */ },
            modifier = Modifier.align(Alignment.BottomEnd).padding(22.dp),
            containerColor = FresqueClimatColors.Primary,
            elevation = FloatingActionButtonDefaults.elevation(10.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Ajouter un atelier")
        }
    }
}


@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(
        modifier = Modifier.background(Color.White)
    )
}