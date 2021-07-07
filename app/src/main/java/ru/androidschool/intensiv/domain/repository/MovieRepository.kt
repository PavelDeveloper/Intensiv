package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.data.movies.vo.MoviesResult

interface MovieRepository {
    fun fetch(): Observable<MoviesResult>
    fun save(movies: List<Movie>): Completable
    fun deleteAll(): Completable
}
