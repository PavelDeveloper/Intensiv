package ru.androidschool.intensiv.data.movies.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    val dates: DatesDto,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class DatesDto(
    val maximum: String,
    val minimum: String
)
