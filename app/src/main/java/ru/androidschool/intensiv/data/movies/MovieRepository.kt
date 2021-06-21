package ru.androidschool.intensiv.data.movies

import io.reactivex.Single
import ru.androidschool.intensiv.network.entity.MoviesResponse

interface MovieRepository {
    fun getMovies(): Single<MoviesResponse>
}
