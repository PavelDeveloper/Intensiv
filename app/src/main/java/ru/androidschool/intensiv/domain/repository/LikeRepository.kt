package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.vo.MoviesResult

interface LikeRepository {
    fun fetch(): Observable<MoviesResult>
    fun delete(id: Long): Completable
    fun getLiked(id: Long): Observable<MovieDbEntity>
    fun update(movie: MovieDbEntity): Completable
}
