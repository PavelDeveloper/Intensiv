package ru.androidschool.intensiv.data.movies

import io.reactivex.Single
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.MoviesResponse
import ru.androidschool.intensiv.utils.on

object UpcomingRepositoryImpl : MovieRepository {

    override fun getMovies(): Single<MoviesResponse> =
        MovieApiClient.api.getUpcomingMovies().on()
}
