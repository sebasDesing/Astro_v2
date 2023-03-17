package com.example.astrop.data

import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.network.AstroApiClient
import com.example.astrop.data.network.AstroService

class AstroRepository {
    private val api = AstroService()
    suspend fun getTypeAstro(): List<AstroTypeModel>? = api.getTypeAstro()
}