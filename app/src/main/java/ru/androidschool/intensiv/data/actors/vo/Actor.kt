package ru.androidschool.intensiv.data.actors.vo

data class Actor(
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    val department: String,
    val name: String,
    val originalName: String,
    val popularity: Float,
    val profilePath: String?,
    val castId: Int,
    val character: String,
    val creditId: String,
    val order: Int
)
