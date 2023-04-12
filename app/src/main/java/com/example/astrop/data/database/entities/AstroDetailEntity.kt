package com.example.astrop.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astrop.domain.model.AstroDetail

@Entity(tableName = "astro_table")
data class AstroDetailEntity(

    @PrimaryKey @ColumnInfo(name = "id_astro") val id_astro: Int,
    @ColumnInfo(name = "name_astro") val name_astro: String,
    @ColumnInfo(name = "type_astro") val type_astro: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "name_com") val name_com: String,
    @ColumnInfo(name = "composition_description") val composition_description: String,
    @ColumnInfo(name = "distance") val distance: Float,
    @ColumnInfo(name = "image_url") val image_url: String,
    @ColumnInfo(name="id_type_astro") val idType:Int
)

fun AstroDetail.toDB() = AstroDetailEntity(
    id_astro=id_astro,
    name_astro=name_astro,
    type_astro=type_astro,
    description=description,
    name_com=name_com,
    composition_description=composition_description,
    distance=distance,
    image_url=image_url,
    idType = id_type_astro
)
