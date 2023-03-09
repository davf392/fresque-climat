package com.davidfz.fresqueclimat.data.local

import com.davidfz.fresqueclimat.data.local.entities.Session
import com.davidfz.fresqueclimat.ui.explore.SessionItem

fun List<Session>.asDomainModel(): List<SessionItem> {
    return map {
        SessionItem(
            id = it.sessionId,
            date = it.date,
            city = it.city,
            country = it.country,
            language = it.language,
            format = it.format,
            price = 15.0,
            description = it.description,
            availableSlotsPublic = it.capacityParticipants - it.totalParticipants,
            totalParticipantsPublic = it.totalParticipants,
            capacitySlotsPublic = it.capacityParticipants,
            availableSlotsFacilitators = it.capacityFacilitators - it.totalFacilitators,
            totalFacilitators = it.totalFacilitators,
            capacitySlotsFacilitators = it.capacityFacilitators,
        )
    }
}
