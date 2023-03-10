package com.example.astrop.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstroTypeModel(
    @SerializedName("type_astro") val typeAstro: String,
    @SerializedName("img_type_astrourl") val imageUrl: String
) : Parcelable
