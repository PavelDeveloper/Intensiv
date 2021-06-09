package ru.androidschool.intensiv.network.entity

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    val page: Int,
    val results: List<TvShow>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class TvShow(
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("vote_average")
    val voteAverage: Double
)
