package ru.androidschool.intensiv.data.movies

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity

interface MovieRepository {
    fun fetch(): Observable<MoviesDomainEntity>
    fun save(movies: List<Movie>): Completable
    fun deleteAll(): Completable
}
