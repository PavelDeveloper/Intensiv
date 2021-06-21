package ru.androidschool.intensiv.data.search

import io.reactivex.Single
import ru.androidschool.intensiv.network.entity.MoviesResponse

interface SearchMoviesRepository {
    fun getSearchMovies(query: CharSequence): Single<MoviesResponse>
}
