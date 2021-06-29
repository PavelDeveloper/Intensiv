package ru.androidschool.intensiv.network.entity

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.DataEntity
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity

data class MoviesResponse(
    val page: Int,
    val results: List<MovieNetworkDataEntity>,
    val dates: Dates,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : DataEntity<MoviesDomainEntity> {
    override fun toDomain(): MoviesDomainEntity {
        return MoviesDomainEntity(
            page = page,
            results = results.map { it.toDomain() }
        )
    }
}

data class MovieNetworkDataEntity(
    @SerializedName("poster_path")
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double
) : DataEntity<Movie> {
    override fun toDomain(): Movie {
        return Movie(
            posterPath = posterPath,
            overview = overview,
            releaseDate = releaseDate,
            id = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteAverage = voteAverage,
            movieType = MovieType.DEFAULT,
            isLiked = false
        )
    }
}

data class Dates(
    val maximum: String,
    val minimum: String
)
