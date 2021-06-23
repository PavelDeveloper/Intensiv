package ru.androidschool.intensiv.domain.entity

import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse

data class MovieDetails(
    val movieInfo: MovieDetailResponse,
    val actors: CreditsResponse
)
