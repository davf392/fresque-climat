package com.davidfz.animfresque.ui.explore.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CastConnected
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.davidfz.animfresque.ui.explore.SessionItem
import com.davidfz.animfresque.ui.explore.ui_states.ExploreUiState
import com.davidfz.animfresque.ui.explore.ui_states.FilterItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


private const val TAG = "ExploreViewModel"

open class ExploreViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    open val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        resetWorkshops()
        resetFilters()
    }

    private fun resetWorkshops() {
        Log.d(TAG, "resetWorkshops")
        _uiState.update { it.copy(workshopList = initWorkshopList()) }
    }

    private fun resetFilters() {
        Log.d(TAG, "resetFilters")
        _uiState.update { it.copy(filtersList = initFiltersList()) }
    }

    fun onWorkshopItemClick(sessionItem: SessionItem) {

    }

    private fun initWorkshopList(): List<SessionItem> {
        return mutableStateListOf(
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
            ),
            SessionItem(
                date = "9 Janvier 2025 à 00:46",
                city = "Grenoble",
                country = "France",
                language = "FR",
                format = "offline",
                price = 30.0,
                description = "Session Fresque du Climat à Grenoble",
                availableSlotsPublic = 5,
                totalParticipantsPublic = 10,
                capacitySlotsPublic = 15,
                availableSlotsFacilitators = 1,
                totalFacilitators = 1,
                capacitySlotsFacilitators = 2
            ),
            SessionItem(
                date = "9 Mars 2025 à 00:46",
                city = "Mexico City",
                country = "Mexique",
                language = "ES",
                format = "offline",
                price = 0.0,
                description = "Taller Fresco Climático en Ciudad de México",
                availableSlotsPublic = 5,
                totalParticipantsPublic = 5,
                capacitySlotsPublic = 10,
                availableSlotsFacilitators = 0,
                totalFacilitators = 2,
                capacitySlotsFacilitators = 2
            ),
            SessionItem(
                date = "12 Septembre 2025 à 14:00",
                city = "Lyon",
                country = "France",
                language = "EN",
                format = "online",
                price = 10.0,
                description = "Online Climate Fresk Workshop",
                availableSlotsPublic = 10,
                totalParticipantsPublic = 20,
                capacitySlotsPublic = 30,
                availableSlotsFacilitators = 2,
                totalFacilitators = 3,
                capacitySlotsFacilitators = 5
            ),
            SessionItem(
                date = "12 Mai 2025 à 14:00",
                city = "Paris",
                country = "France",
                language = "FR",
                format = "offline",
                price = 28.0,
                description = "Atelier Fresque du Climat à Lyon",
                availableSlotsPublic = 8,
                totalParticipantsPublic = 12,
                capacitySlotsPublic = 20,
                availableSlotsFacilitators = 1,
                totalFacilitators = 2,
                capacitySlotsFacilitators = 3
            )
        )
    }

    private fun initFiltersList(): List<FilterItem> {
        return mutableStateListOf(
            FilterItem("filters", Icons.Filled.Tune, {}),
            FilterItem("rayon", Icons.Filled.PinDrop, {}),
            FilterItem("language", Icons.Filled.Translate, {}),
            FilterItem("online", Icons.Filled.CastConnected, {}),
            FilterItem("catégorie", Icons.Filled.CastConnected, {}),
        )
    }
}