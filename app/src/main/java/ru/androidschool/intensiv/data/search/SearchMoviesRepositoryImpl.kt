package ru.androidschool.intensiv.data.search

import io.reactivex.Single
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.MoviesResponse
import ru.androidschool.intensiv.utils.on

object SearchMoviesRepositoryImpl : SearchMoviesRepository {
    override fun getSearchMovies(query: CharSequence): Single<MoviesResponse> =
        MovieApiClient.api.getSearchMovies(query).on()
}
