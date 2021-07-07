package ru.androidschool.intensiv.data.tvshows.dto

import com.google.gson.annotations.SerializedName

data class TvShowsResponseDto(
    val page: Int,
    val results: List<TvShowDto>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
