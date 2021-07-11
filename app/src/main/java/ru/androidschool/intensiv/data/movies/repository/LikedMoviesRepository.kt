package ru.androidschool.intensiv.data.movies.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.db.AppDatabase
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.mappers.MovieDbMapper
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.LikeRepository

class LikedMoviesRepository(private val db: AppDatabase) : LikeRepository {

    override fun fetch(): Observable<MoviesResult> =
        db.movieDao().getLiked().map {
            MoviesResult(
                page = 1,
                results = MovieDbMapper.toViewObject(it)
            )
        }

    override fun delete(id: Long): Completable = db.movieDao().delete(id)

    override fun getLiked(id: Long): Observable<MovieDbEntity> = db.movieDao().get(id)

    override fun update(movie: MovieDbEntity): Completable = db.movieDao().update(movie)
}
