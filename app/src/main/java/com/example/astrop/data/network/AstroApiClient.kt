package com.example.astrop.data.network

import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.model.DailyImageModel
import retrofit2.Response
import retrofit2.http.GET

interface AstroApiClient
{

    @GET("/api/astros/types")
    suspend fun getAstroTypes(): Response <List<AstroTypeModel>>
    @GET("/api/astros/daily")
    suspend fun  getDailyImage(): Response<DailyImageModel>
}