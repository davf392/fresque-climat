package com.davidfz.animfresque.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidfz.animfresque.ui.home.components.ProfileStatsOverview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(8.dp).fillMaxHeight()) {
        ProfileStatsOverview()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.background(Color.White)
    )
}