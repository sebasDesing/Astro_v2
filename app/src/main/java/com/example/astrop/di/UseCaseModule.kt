package com.example.astrop.di

import com.example.astrop.domain.use_case.get_astro_detail.GetAstroDetailUseCase
import com.example.astrop.domain.use_case.get_astro_detail.GetAstrosDetailUseCaseImpl
import com.example.astrop.domain.use_case.get_astro_type.GetAstroTypeUseCase
import com.example.astrop.domain.use_case.get_astro_type.GetAstroTypeUseCaseImpl
import com.example.astrop.domain.use_case.get_daily_image.GetDailyImageUseCase
import com.example.astrop.domain.use_case.get_daily_image.GetDailyImageUseCaseImpl
import com.example.astrop.domain.use_case.get_detail_by_type.GetDetailByTypeUseCase
import com.example.astrop.domain.use_case.get_detail_by_type.GetDetailByTypeUseCaseImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindsDailyImageUseCase(getDailyImageUseCaseImpl: GetDailyImageUseCaseImpl): GetDailyImageUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsGetDetailByTypeUseCase(getDetailByTypeUseCaseImpl: GetDetailByTypeUseCaseImpl): GetDetailByTypeUseCase
}