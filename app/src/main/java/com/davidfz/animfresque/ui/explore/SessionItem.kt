package com.davidfz.animfresque.ui.explore

data class SessionItem(
    val id: Long = 0L,
    val date: String = "",
    val city: String = "",
    val country: String = "",
    val language: String = "",
    val format: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val availableSlotsPublic: Int = 0,
    val totalParticipantsPublic: Int = 0,
    val capacitySlotsPublic: Int = 0,
    val availableSlotsFacilitators: Int = 0,
    val totalFacilitators: Int = 0,
    val capacitySlotsFacilitators: Int = 0
) {
    fun getLocationString(): String =
        if(format.contentEquals("online")) "En ligne" else "$city, $country"
}
