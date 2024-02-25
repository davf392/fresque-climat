package com.davidfz.fresqueclimat.di

import com.davidfz.fresqueclimat.data.remote.repositories.FakeProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object FakeRepositoryModule {

    @Provides
    fun provideFakeProfileRepository(): FakeProfileRepository {
        return FakeProfileRepository()
    }
}