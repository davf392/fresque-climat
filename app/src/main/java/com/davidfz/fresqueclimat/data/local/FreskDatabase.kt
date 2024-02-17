package com.davidfz.fresqueclimat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidfz.fresqueclimat.data.local.dao.ProfileDao
import com.davidfz.fresqueclimat.data.local.dao.ResourceDao
import com.davidfz.fresqueclimat.data.local.dao.SessionDao
import com.davidfz.fresqueclimat.data.local.entities.ResourceEntity
import com.davidfz.fresqueclimat.data.local.entities.SessionEntity
import com.davidfz.fresqueclimat.data.local.entities.ProfileItem
import com.davidfz.fresqueclimat.utils.StringListConverter


@Database(
    entities = [
        SessionEntity::class,
        ResourceEntity::class,
        ProfileItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class FreskDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun resourceDao(): ResourceDao
    abstract fun profileDao(): ProfileDao
}