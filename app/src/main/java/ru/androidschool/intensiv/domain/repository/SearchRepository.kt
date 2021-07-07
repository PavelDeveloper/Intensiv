package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.movies.vo.MoviesResult

interface SearchRepository {
    fun getSearchMovies(query: CharSequence): Single<MoviesResult>
}
