package com.example.astrop.data.network

import com.example.astrop.data.model.AstroTypeModel
import javax.inject.Inject

class AstroService @Inject constructor(private val apiClient: AstroApiClient) {


    suspend fun getTypeAstro(): List<AstroTypeModel> {
        val res = apiClient.getAstroTypes()
        return res.body() ?: emptyList()
    }


}