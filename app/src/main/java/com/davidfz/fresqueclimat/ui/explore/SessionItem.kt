package com.davidfz.fresqueclimat.ui.explore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionItem(
    val id: Long,
    val date: String,
    val city: String,
    val country: String,
    val language: String,
    val format: String,
    val price: Double,
    val description: String,
    val availableSlotsPublic: Int,
    val totalParticipantsPublic: Int,
    val capacitySlotsPublic: Int,
    val availableSlotsFacilitators: Int,
    val totalFacilitators: Int,
    val capacitySlotsFacilitators: Int
)
    : Parcelable {

        fun getLocationString(): String =
            if(format.contentEquals("online")) "En ligne" else "$city, $country"
    }
