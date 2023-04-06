package com.example.astrop.domain.model

import android.os.Parcelable
import com.example.astrop.data.database.entities.AstroDetailEntity
import com.example.astrop.data.model.AstroDetailModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstroDetail(
    val id_astro: Int,
    val name_astro: String,
    val type_astro: String,
    val description: String,
    val name_com: String,
    val composition_description: String,
    val distance: Float,
    val image_url: String
) : Parcelable

fun AstroDetailModel.toDomain() = AstroDetail(
    id_astro,
    name_astro,
    type_astro,
    description,
    name_com,
    composition_description,
    distance,
    image_url
)

fun AstroDetailEntity.toDomain() = AstroDetail(
    id_astro,
    name_astro,
    type_astro,
    description,
    name_com,
    composition_description,
    distance,
    image_url
)
