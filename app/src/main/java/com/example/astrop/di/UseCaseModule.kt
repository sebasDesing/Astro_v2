package com.example.astrop.di

import com.example.astrop.domain.use_case.get_astro_detail.GetAstroDetailUseCase
import com.example.astrop.domain.use_case.get_astro_detail.GetAstrosDetailUseCaseImpl
import com.example.astrop.domain.use_case.get_astro_type.GetAstroTypeUseCase
import com.example.astrop.domain.use_case.get_astro_type.GetAstroTypeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsGetAstrosDetailUseCase(getAstrosDetailUseCaseImpl: GetAstrosDetailUseCaseImpl): GetAstroDetailUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindsGetAstroTypeUseCase(getAstroTypeUseCaseImpl: GetAstroTypeUseCaseImpl): GetAstroTypeUseCase
}