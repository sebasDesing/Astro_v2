package com.example.astrop.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiNasaHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}