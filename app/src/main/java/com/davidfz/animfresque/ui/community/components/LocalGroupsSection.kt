package com.davidfz.animfresque.ui.community.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LocalGroupsSection(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        ClearableSearchBar(label = "Saisissez votre ville ou pays")
    }
}


@Preview
@Composable
fun LocalGroupsSectionPreview() {
    LocalGroupsSection(modifier = Modifier.background(Color.White))
}