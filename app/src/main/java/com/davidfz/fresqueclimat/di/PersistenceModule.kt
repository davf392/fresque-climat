package com.davidfz.fresqueclimat.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.davidfz.fresqueclimat.data.local.FreskDatabase
import com.davidfz.fresqueclimat.data.local.dao.ResourceDao
import com.davidfz.fresqueclimat.data.local.dao.SessionDao
import com.davidfz.fresqueclimat.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideFreskDatabase(@NonNull app: Application): FreskDatabase =
        Room.databaseBuilder(
            app,
            FreskDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideSessionDao(@NonNull database: FreskDatabase): SessionDao =
        database.sessionDao()

    @Provides
    @Singleton
    fun provideResourceDao(@NonNull database: FreskDatabase): ResourceDao =
        database.resourceDao()
}