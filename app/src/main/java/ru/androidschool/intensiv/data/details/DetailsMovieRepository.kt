package ru.androidschool.intensiv.data.details

import io.reactivex.Single
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import ru.androidschool.intensiv.utils.on

object DetailsMovieRepository : DetailsRepository {
    override fun getDetails(id: Long): Single<MovieDetailResponse> =
        MovieApiClient.api.getMovieDetail(movieId = id).on()
}
