package com.idplus.fresqueclimat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idplus.fresqueclimat.data.Session
import com.idplus.fresqueclimat.data.SessionDao
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class SessionViewModel(val dao: SessionDao) : ViewModel() {

    val sessions = dao.getAll()

    fun seedFakeData() {
        viewModelScope.launch {

            // insert fake data for testing
            val formattedDate = SimpleDateFormat("d LLLL yyyy").format(Date())
            val formattedTime = SimpleDateFormat("HH:mm").format(Date())
            val dateTime = "$formattedDate à $formattedTime"

            dao.insertAll(
                arrayOf(
                    Session(
                        1, dateTime, "Lyon", "France",
                        2, "presential", "atelier", "fr", 5, 1, 16, 2,
                        "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                        "David Fourdrigniez",
                        "davidfourdrigniez@protonmail.com"
                    ),
                    Session(2, dateTime, "Marseille", "France",
                        3, "presential", "atelier", "fr", 12, 0, 14, 2,
                        "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                        "David Fourdrigniez",
                        "davidfourdrigniez@protonmail.com"
                    ),
                    Session(3, dateTime, "", "",
                        3, "online", "workshop", "it", 12, 2, 21, 3,
                        "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                        "David Fourdrigniez",
                        "davidfourdrigniez@protonmail.com"
                    ),
                    Session(4, dateTime, "Mexico City", "Mexique",
                        3, "presential", "atelier", "fr", 12, 1, 7, 1,
                        "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                        "David Fourdrigniez",
                        "davidfourdrigniez@protonmail.com"
                    ),
                    Session(5, dateTime, "Grenoble", "France",
                        3, "presential", "atelier", "ar", 12, 0, 7, 1,
                        "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                        "David Fourdrigniez",
                        "davidfourdrigniez@protonmail.com"
                    ),
                    Session(6, dateTime, "Marseille", "France",
                        3, "presential", "atelier", "fr", 12, 4, 28, 4,
                        "L\'organisateur·rice n\'a pas écrit de description pour cette session",
                        "David Fourdrigniez",
                        "davidfourdrigniez@protonmail.com"
                    )
                )
            )
        }
    }
}
