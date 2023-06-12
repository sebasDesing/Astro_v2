package com.example.astrop.domain.use_case.get_astro_type

import com.example.astrop.data.AstroRepositoryImpl
import com.example.astrop.data.database.entities.toDB
import com.example.astrop.domain.model.AstroType
import javax.inject.Inject

class GetAstroTypeUseCaseImpl @Inject constructor(private val repository: AstroRepositoryImpl) :
    GetAstroTypeUseCase {

    override suspend operator fun invoke(): List<AstroType> {
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