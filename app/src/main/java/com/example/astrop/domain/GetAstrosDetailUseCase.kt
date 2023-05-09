package com.example.astrop.domain

import com.example.astrop.data.AstroRepositoryImpl
import com.example.astrop.data.database.entities.toDB
import com.example.astrop.domain.model.AstroDetail
import javax.inject.Inject

class GetAstrosDetailUseCase @Inject constructor(
    private val repository
    : AstroRepositoryImpl
) {

    suspend operator fun  invoke(): List<AstroDetail> {

        val astroDetail = repository.getAllAstrosDetailFromBD()
        return if (astroDetail.isNotEmpty()) {
            astroDetail
        } else {
            val apiAstroDetail = repository.getAllAstrosDetailFromApi()
            if (apiAstroDetail.isNotEmpty()) {
                repository.clearAstroDetail()
                repository.insertAstroDetail(apiAstroDetail.map {
                    it.toDB()
                })
                apiAstroDetail
            } else {
                emptyList()
            }
        }

    }
}