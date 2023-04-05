package com.example.astrop.domain

import com.example.astrop.data.AstroRepository
import com.example.astrop.domain.model.AstroDetail
import javax.inject.Inject

class GetAstrosDetailUseCase @Inject constructor(
    private val repository
    : AstroRepository
) {

    suspend operator fun  invoke(): List<AstroDetail>{

        val astroDetail = repository.getAllAstrosDetailFromApi()
        return if (astroDetail.isNotEmpty()){
            astroDetail
        }else{
            emptyList()
        }
    }

}