package ru.androidschool.intensiv.data.movies

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity

interface MovieRepository {
    fun fetch(): Flowable<MoviesDomainEntity>?
    fun save(movies: List<Movie>): Completable?
    fun deleteAll(): Completable?
    fun delete(id: Long): Completable?
}
