package com.idplus.fresqueclimat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Session::class, Resource::class], version = 1, exportSchema = false)
abstract class FreskDatabase : RoomDatabase() {
    abstract val sessionDao: SessionDao
    abstract val resourceDao: ResourceDao

    companion object {
        @Volatile
        private var INSTANCE:FreskDatabase? = null

        fun getInstance(context: Context): FreskDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FreskDatabase::class.java,
                        "fresk_database"
                    ).build()
                }

                return instance
            }
        }
    }
}