package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.davidfz.fresqueclimat.data.local.dao.SessionDao
import com.davidfz.fresqueclimat.data.local.entities.SessionEntity
import com.davidfz.fresqueclimat.data.local.entities.asDomainModel
import com.davidfz.fresqueclimat.ui.explore.SessionItem
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val dao: SessionDao
)
    : SessionRepository {

    override fun observeAllSessions(): LiveData<List<SessionItem>> {

        return Transformations.map(dao.getAll()) { it.asDomainModel() }
    }

    override suspend fun refreshSessions() {
        val formattedDate = SimpleDateFormat("d LLLL yyyy").format(Date())
        val formattedTime = SimpleDateFormat("HH:mm").format(Date())
        val dateTime = "$formattedDate à $formattedTime"

        dao.insertAll(
            arrayOf(
                SessionEntity(
                    1, dateTime, "Lyon", "France",
                    2, "presential", "atelier", "fr",
                    5, 4, 2, 2,
                    "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                    "David Fourdrigniez",
                    "davidfourdrigniez@protonmail.com"
                ),
                SessionEntity(2, dateTime, "Marseille", "France",
                    3, "presential", "atelier", "fr",
                    12, 4, 5, 2,
                    "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                    "David Fourdrigniez",
                    "davidfourdrigniez@protonmail.com"
                ),
                SessionEntity(3, dateTime, "", "",
                    3, "online", "workshop", "it",
                    12, 3, 2, 3,
                    "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                    "David Fourdrigniez",
                    "davidfourdrigniez@protonmail.com"
                ),
                SessionEntity(4, dateTime, "Mexico City", "Mexique",
                    3, "presential", "atelier", "fr",
                    12, 1, 7, 1,
                    "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                    "David Fourdrigniez",
                    "davidfourdrigniez@protonmail.com"
                ),
                SessionEntity(5, dateTime, "Grenoble", "France",
                    3, "presential", "atelier", "ar",
                    12, 2, 7, 1,
                    "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                    "David Fourdrigniez",
                    "davidfourdrigniez@protonmail.com"
                ),
                SessionEntity(6, dateTime, "Marseille", "France",
                    3, "presential", "atelier", "fr",
                    12, 4, 9, 4,
                    "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                    "David Fourdrigniez",
                    "davidfourdrigniez@protonmail.com"
                )
            )
        )
    }
}