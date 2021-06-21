package ru.androidschool.intensiv.data.details

import io.reactivex.Single
import ru.androidschool.intensiv.network.entity.MovieDetailResponse

interface DetailsMovieRepository {
    fun getMovieDetails(id: Int): Single<MovieDetailResponse>
}
