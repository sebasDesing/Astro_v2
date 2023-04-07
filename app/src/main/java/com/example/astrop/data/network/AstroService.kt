package com.example.astrop.data.network

import com.example.astrop.data.model.AstroDetailModel
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.model.DailyImageModel
import retrofit2.Response
import javax.inject.Inject

class AstroService @Inject constructor(private val apiClient: AstroApiClient) {


    suspend fun getTypeAstro(): List<AstroTypeModel> {
        val res = apiClient.getAstroTypes()
        return res.body() ?: emptyList()
    }

    suspend fun  getDailyImage(): Response<DailyImageModel> {
        return apiClient.getDailyImage()
    }
    suspend fun getAllAstros(): List<AstroDetailModel>{
        val res = apiClient.getAllAstros()
        return res.body() ?: emptyList()
    }


}