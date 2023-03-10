package com.example.astrop.data.network

import com.example.astrop.core.RetrofitHelper
import com.example.astrop.data.model.AstroTypeModel

class AstroService {

    private val rtf = RetrofitHelper.getRetrofit()
    suspend fun getTypeAstro(): AstroTypeModel? {
        val res = rtf.create(AstroApiClient::class.java).getAstroTypes()
        return res.body()
    }
}