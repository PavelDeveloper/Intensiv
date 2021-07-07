package ru.androidschool.intensiv.data.movies.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDbEntity(
    @PrimaryKey @ColumnInfo(name = "movieId")
    val movieId: Long,
    val posterPath: String?,
    val overview: String,
    val releaseDate: String?,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String?,
    val popularity: Double,
    val voteAverage: Double,
    val isLiked: Boolean,
    val movieType: MovieType
)
