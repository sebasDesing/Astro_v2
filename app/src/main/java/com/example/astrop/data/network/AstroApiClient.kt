package com.example.astrop.data.network

import com.example.astrop.data.model.AstroDetailModel
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.model.DailyImageModel
import retrofit2.Response
import retrofit2.http.GET

interface AstroApiClient
{

    @GET("/api/astros/types")
    suspend fun getAstroTypes(): Response <List<AstroTypeModel>>
    @GET("/planetary/apod?api_key=wivo5ZcPxL23teiy0LlcS7Pxtt6tK4HOfASK6RAZ")
    suspend fun  getDailyImage(): Response<DailyImageModel>

    @GET("/api/astros/all")
    suspend fun  getAllAstros(): Response <List<AstroDetailModel>>

}