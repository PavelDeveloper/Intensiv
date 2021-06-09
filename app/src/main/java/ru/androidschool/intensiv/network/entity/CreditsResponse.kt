package ru.androidschool.intensiv.network.entity

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    val id: Int,
    val cast: List<Actor>
)

data class Actor(
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    @SerializedName("known_for_department")
    val department: String,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Float,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("castId")
    val cast_id: Int,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val order: Int
)
