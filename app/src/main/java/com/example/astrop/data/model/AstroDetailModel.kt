package com.example.astrop.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstroDetailModel(
    val id_astro: Int,
    val name_astro: String,
    val type_astro: String,
    val description: String,
    val name_com: String,
    val composition_description: String,
    val distance: Float,
    val image_url: String,
    val id_type_astro:Int
) : Parcelable
