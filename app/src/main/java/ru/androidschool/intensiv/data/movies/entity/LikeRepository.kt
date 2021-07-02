package ru.androidschool.intensiv.data.movies.entity

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity

interface LikeRepository {
    fun fetch(): Observable<MoviesDomainEntity>
    fun delete(id: Long): Completable
    fun getLiked(id: Long): Observable<MovieDbEntity>
    fun update(movie: MovieDbEntity): Completable
}
