package com.davidfz.fresqueclimat.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.davidfz.fresqueclimat.data.local.DatabaseCallback
import com.davidfz.fresqueclimat.data.local.FreskDatabase
import com.davidfz.fresqueclimat.data.local.dao.ProfileDao
import com.davidfz.fresqueclimat.data.local.dao.ResourceDao
import com.davidfz.fresqueclimat.data.local.dao.SessionDao
import com.davidfz.fresqueclimat.data.local.entities.ProfileItem
import com.davidfz.fresqueclimat.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabaseCallback(): DatabaseCallback {
        return DatabaseCallback()
    }

    @Provides
    @Singleton
    fun provideFreskDatabase(@NonNull app: Application, databaseCallback: DatabaseCallback): FreskDatabase =
        Room.databaseBuilder(app, FreskDatabase::class.java, DATABASE_NAME)
            .addCallback(databaseCallback)
            .build()

    @Provides
    @Singleton
    fun provideSessionDao(@NonNull database: FreskDatabase): SessionDao =
        database.sessionDao()

    @Provides
    @Singleton
    fun provideResourceDao(@NonNull database: FreskDatabase): ResourceDao =
        database.resourceDao()

    @Provides
    @Singleton
    fun provideProfileDao(@NonNull database: FreskDatabase): ProfileDao =
        database.profileDao()
}