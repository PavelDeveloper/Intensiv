package ru.androidschool.intensiv.domain.entity

import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.DataEntity

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
) : DataEntity<MovieDbEntity> {
    override fun toDomain(): MovieDbEntity {
        return MovieDbEntity(
            posterPath = posterPath,
            overview = overview,
            releaseDate = releaseDate,
            movieId = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            backdropPath = backdropPath,
            popularity = popularity,
            voteAverage = voteAverage,
            title = title,
            movieType = movieType,
            isLiked = isLiked
        )
    }
}
