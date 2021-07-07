package ru.androidschool.intensiv.data.tvshows.dto

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.BuildConfig

data class TvShowDto(
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
) {
    @SerializedName("poster_path")
    val posterPath: String? = null
        get() = "${BuildConfig.POSTER_BASE_URL}$field"
}
