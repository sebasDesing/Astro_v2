package com.example.astrop.di

import android.content.Context
import androidx.room.Room
import com.example.astrop.data.database.Astrodb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val ASTRO_TYPE_DB_NAME = "astro_types"
    private const val DAILY_IMAGE_DB_NAME = "daily_image"

    @Singleton
    @Provides
    fun provifrRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Astrodb::class.java, ASTRO_TYPE_DB_NAME).build()



    @Singleton
    @Provides
    fun provideAstroTypeDao(bds: Astrodb) = bds.getAstroTypeDao()

    @Singleton
    @Provides
    fun provideDailyImage(bds: Astrodb) = bds.getDailyImageDao()


}