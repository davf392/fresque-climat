package com.davidfz.fresqueclimat.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DatabaseCallback: RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            // Insert mock data into the database using SQL queries
            db.execSQL("INSERT INTO profile (first_name, last_name, home_city, email, phone_number, languages, isPublic, anim_company, anim_commercial, profile_picture_uri) " +
                    "VALUES ('David', 'Fourdrigniez', 'Lyon', 'davidfourdrigniez@protonmail.com', '+33627940325', 'French, English', 1, 0, 0, null)")
            // Add more mock data as needed
        }
    }
}