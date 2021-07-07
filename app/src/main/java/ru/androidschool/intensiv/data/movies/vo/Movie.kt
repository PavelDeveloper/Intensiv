package ru.androidschool.intensiv.data.movies.vo

import ru.androidschool.intensiv.data.movies.entity.MovieType

data class Movie(
    val posterPath: String?,
    val overview: String,
    val releaseDate: String?,
    val id: Long,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String?,
    val popularity: Double,
    val voteAverage: Double,
    val movieType: MovieType,
    val isLiked: Boolean
)
