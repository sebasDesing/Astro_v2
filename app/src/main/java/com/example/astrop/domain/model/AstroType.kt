package com.example.astrop.domain.model

import com.example.astrop.data.database.entities.AstroTypeEntity
import com.example.astrop.data.model.AstroTypeModel

data class AstroType(val id_type_astro:Int,val typeAstro: String, val imgUrl: String)

fun AstroTypeModel.toDomain() = AstroType(idType,typeAstro, imageUrl)
fun AstroTypeEntity.toDomain() = AstroType(idType,typeAstro, imageUrl)
