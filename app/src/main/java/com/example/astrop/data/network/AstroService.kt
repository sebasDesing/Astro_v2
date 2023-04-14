package com.example.astrop.data.network

import com.example.astrop.data.model.AstroDetailModel
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.model.DailyImageModel
import com.example.astrop.di.ApiNasaHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AstroService @Inject constructor(private val apiClient: AstroApiClient) {
    private val apiNasa= ApiNasaHelper

    suspend fun getTypeAstro(): List<AstroTypeModel> {
        val res = apiClient.getAstroTypes()
        return res.body() ?: emptyList()
    }

    suspend fun  getDailyImage(): Response<DailyImageModel> {

        return withContext(Dispatchers.IO) {

         apiNasa.getRetrofit().create(AstroApiClient::class.java).getDailyImage()

        }
    }
    suspend fun getAllAstros(): List<AstroDetailModel>{
        val res = apiClient.getAllAstros()
        return res.body() ?: emptyList()
    }


}