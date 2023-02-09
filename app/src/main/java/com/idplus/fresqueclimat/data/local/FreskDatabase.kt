package com.idplus.fresqueclimat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idplus.fresqueclimat.data.local.dao.ResourceDao
import com.idplus.fresqueclimat.data.local.dao.SessionDao
import com.idplus.fresqueclimat.data.local.entities.Resource
import com.idplus.fresqueclimat.data.local.entities.Session


@Database(
    entities = [
        Session::class,
        Resource::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FreskDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun resourceDao(): ResourceDao
}