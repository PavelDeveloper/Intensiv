package ru.androidschool.intensiv.data.tvshows.vo

data class TvShowsResult(
    val page: Int,
    val results: List<TvShow>,
    val totalResults: Int,
    val totalPages: Int
)
