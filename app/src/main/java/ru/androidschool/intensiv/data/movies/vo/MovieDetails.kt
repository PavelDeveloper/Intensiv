package ru.androidschool.intensiv.data.movies.vo

import ru.androidschool.intensiv.data.actors.vo.Credits
import ru.androidschool.intensiv.data.details.vo.MovieDetailInfo

data class MovieDetails(
    val movieDetailInfo: MovieDetailInfo,
    val actors: Credits
)
