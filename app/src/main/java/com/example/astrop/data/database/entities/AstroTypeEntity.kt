package com.example.astrop.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astrop.domain.model.AstroType

@Entity(tableName = "astroType_table")
data class AstroTypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val  id:Int=0,
    @ColumnInfo(name = "typeAstro") val typeAstro: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "idType") val idType :Int
)

fun AstroType.toDB() = AstroTypeEntity(typeAstro = typeAstro, imageUrl = imgUrl, idType = id_type_astro)