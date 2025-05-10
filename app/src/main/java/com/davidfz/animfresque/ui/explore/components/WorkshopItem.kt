package com.davidfz.animfresque.ui.explore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidfz.animfresque.R
import com.davidfz.animfresque.ui.explore.SessionItem

@Composable
fun WorkshopItem(
    workshop: SessionItem = SessionItem(),
    onClickWorkShopItem: (SessionItem) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickWorkShopItem(workshop) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Search, contentDescription = "Date/Time", modifier = Modifier.padding(end = 8.dp).size(16.dp))
                    Text(text = workshop.date)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Map, contentDescription = "Location", modifier = Modifier.padding(end = 8.dp).size(16.dp))
                    Text(text = "${workshop.city}, ${workshop.country}")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Person, contentDescription = "Places", modifier = Modifier.padding(end = 8.dp).size(16.dp))
                    Text(text = stringResource(R.string.session_nb_slots_participants, workshop.availableSlotsPublic))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Person, contentDescription = "Animators", modifier = Modifier.padding(end = 8.dp).size(16.dp))
                    Text(text = stringResource(R.string.session_nb_slots_facilitators, workshop.availableSlotsFacilitators))
                }
            }
        }
    }
}

@Preview
@Composable
fun WorkshopItemPrevies() {
    WorkshopItem(
        SessionItem(
            date = "9 Mars 2023 à 17:30",
            city = "Marseille",
            country = "France",
            language = "FR",
            format = "offline",
            price = 25.0,
            description = "Atelier Fresque du Climat à Marseille",
            availableSlotsPublic = 3,
            totalParticipantsPublic = 12,
            capacitySlotsPublic = 15,
            availableSlotsFacilitators = 0,
            totalFacilitators = 2,
            capacitySlotsFacilitators = 2
        )
    )
}