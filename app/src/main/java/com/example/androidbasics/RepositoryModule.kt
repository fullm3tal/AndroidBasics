package com.example.androidbasics

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesMindbodyInformationRepository(

    ): ITaskDetailRepository {
        return TaskDetailRepository()
    }

}