package com.idplus.fresqueclimat.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.idplus.fresqueclimat.data.local.FreskDatabase
import com.idplus.fresqueclimat.data.local.dao.ResourceDao
import com.idplus.fresqueclimat.data.local.dao.SessionDao
import com.idplus.fresqueclimat.data.remote.repositories.ResourcesRepository
import com.idplus.fresqueclimat.data.remote.repositories.ResourcesRepositoryImpl
import com.idplus.fresqueclimat.data.remote.repositories.SessionRepository
import com.idplus.fresqueclimat.data.remote.repositories.SessionRepositoryImpl
import com.idplus.fresqueclimat.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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