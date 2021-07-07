package ru.androidschool.intensiv.data.details.dto

import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso: String,
    val name: String
)
