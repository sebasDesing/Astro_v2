package com.example.astrop.di


import com.example.astrop.data.network.AstroApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val BASE_URL = "http://192.168.247.33:3000"
    @Singleton
    @Provides

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides

    fun provideAstroApiClient(retrofit: Retrofit): AstroApiClient {
        return retrofit.create(AstroApiClient::class.java)
    }
}
