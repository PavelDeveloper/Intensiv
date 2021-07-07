package ru.androidschool.intensiv.data.details.dto

import com.google.gson.annotations.SerializedName

data class SpokenLanguageDto(
    @SerializedName("iso_639_1")
    val iso: String,
    val name: String
)
