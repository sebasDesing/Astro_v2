package com.example.astrop.domain

import com.example.astrop.data.AstroRepository
import com.example.astrop.data.database.entities.toDB
import com.example.astrop.domain.model.AstroType
import javax.inject.Inject

class GetAstroTypeUseCase @Inject constructor(private val repository: AstroRepository) {

    suspend operator fun invoke(): List<AstroType> {
        val astroTypes = repository.getTypeAstroFromDB()
        return if (astroTypes.isNotEmpty()) {
            astroTypes
        } else {
            val apiAstroTypes = repository.getTypeAstroFromApi()
            if (apiAstroTypes.isNotEmpty()) {
                repository.clearAstroTypes()
                repository.insertAstroTypes(apiAstroTypes.map {
                    it.toDB()
                })
                apiAstroTypes
            } else {
                emptyList()
            }
        }
    }
}