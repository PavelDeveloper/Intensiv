package ru.androidschool.intensiv.data.tvshows.vo

data class TvShow(
    val overview: String,
    val genreIds: List<Int>,
    val id: Int,
    val originCountry: List<String>,
    val firstAirDate: String,
    val originalLanguage: String,
    val name: String,
    val originalName: String,
    val backdropPath: String?,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double,
    val posterPath: String?
)
