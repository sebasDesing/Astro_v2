package com.example.astrop.di

import com.example.astrop.data.AstroRepository
import com.example.astrop.data.AstroRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindsAstrosRepository(astroRepositoryImpl: AstroRepositoryImpl): AstroRepository
}