package com.davidfz.fresqueclimat.di

import com.davidfz.fresqueclimat.data.remote.repositories.FakeProfileRepository
import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepository
import com.davidfz.fresqueclimat.data.remote.repositories.ResourcesRepository
import com.davidfz.fresqueclimat.data.remote.repositories.ResourcesRepositoryImpl
import com.davidfz.fresqueclimat.data.remote.repositories.SessionRepository
import com.davidfz.fresqueclimat.data.remote.repositories.SessionRepositoryImpl
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

    @Binds
    abstract fun bindProfileRepository(impl: FakeProfileRepository): ProfileRepository
}