package com.davidfz.animfresque.ui.community.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MembersSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClearableSearchBar(label = "Nom, prénom ou email")
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // TODO: Populate with directory items
        }
    }
}

@Preview
@Composable
fun MembersSectionPreview() {
    MembersSection(
        modifier = Modifier.background(Color.White)
    )
}