package ru.androidschool.intensiv.data.movies.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.androidschool.intensiv.domain.DataEntity
import ru.androidschool.intensiv.domain.entity.Movie

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
) : DataEntity<Movie> {
    override fun toDomain(): Movie {
        return Movie(
            id = movieId,
            posterPath = posterPath,
            overview = overview,
            releaseDate = releaseDate,
            originalTitle = originalTitle,
            originalLanguage = originalTitle,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteAverage = voteAverage,
            isLiked = isLiked,
            movieType = movieType
        )
    }
}
