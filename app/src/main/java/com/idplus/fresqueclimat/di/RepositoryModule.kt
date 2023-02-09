package com.idplus.fresqueclimat.di

import com.idplus.fresqueclimat.data.remote.repositories.ResourcesRepository
import com.idplus.fresqueclimat.data.remote.repositories.ResourcesRepositoryImpl
import com.idplus.fresqueclimat.data.remote.repositories.SessionRepository
import com.idplus.fresqueclimat.data.remote.repositories.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindSessionRepository(impl: SessionRepositoryImpl): SessionRepository

    @Binds
    abstract fun bindResourcesRepository(impl: ResourcesRepositoryImpl): ResourcesRepository
}