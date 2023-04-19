package com.example.astrop.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiNasaHelper {
/* OBTIENE LA INFORMACION DE LA NASA, DE MANERA PROVICIONAL, YA QUE NUESTRO HOST REMOTO
NOS RESTRINGE HACER PETICIONES DESDE NUESTRA MISMA API */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}