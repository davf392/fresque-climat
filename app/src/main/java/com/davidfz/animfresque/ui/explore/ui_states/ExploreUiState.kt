package com.davidfz.animfresque.ui.explore.ui_states

import com.davidfz.animfresque.ui.explore.SessionItem


data class ExploreUiState(
    val workshopList: List<SessionItem> = listOf(),
    val filtersList: List<FilterItem> = listOf()
)