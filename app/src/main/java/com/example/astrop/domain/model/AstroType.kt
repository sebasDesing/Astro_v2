package com.example.astrop.domain.model

import com.example.astrop.data.database.entities.AstroTypeEntity
import com.example.astrop.data.model.AstroTypeModel

data class AstroType(val typeAstro: String, val imgUrl: String)

fun AstroTypeModel.toDomain() = AstroType(typeAstro, imageUrl)
fun AstroTypeEntity.toDomain() = AstroType(typeAstro, imageUrl)
