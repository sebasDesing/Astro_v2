package com.example.astrop.data

import com.example.astrop.data.database.dao.AstroTypeDao
import com.example.astrop.data.database.entities.AstroTypeEntity
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.network.AstroService
import com.example.astrop.domain.model.AstroType
import com.example.astrop.domain.model.toDomain
import javax.inject.Inject

class AstroRepository @Inject constructor(private val api: AstroService, private val AstroTypeDao: AstroTypeDao) {

    suspend fun getTypeAstroFromApi(): List<AstroType> {
        val response: List<AstroTypeModel> = api.getTypeAstro()
        return response.map {
            it.toDomain()
        }
    }
    suspend fun getTypeAstroFromDB(): List<AstroType> {
        val response: List<AstroTypeEntity> = AstroTypeDao.geAstroTypes()
        return response.map {
            it.toDomain()
        }
    }

    suspend fun insertAstroTypes(astroTypes:List<AstroTypeEntity>){
        AstroTypeDao.insertAll(astroTypes)
    }

    suspend fun  clearAstroTypes(){
        AstroTypeDao.deleteAllAstroTypes()
    }

}