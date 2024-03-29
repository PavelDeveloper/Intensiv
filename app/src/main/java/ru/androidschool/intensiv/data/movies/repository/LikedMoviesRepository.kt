package ru.androidschool.intensiv.data.movies.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.movies.db.MoviesDao
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.mappers.MovieDbMapper
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.LikeRepository

class LikedMoviesRepository(private val db: MoviesDao) : LikeRepository {

    override fun fetch(): Observable<MoviesResult> =
        db.getLiked().map {
            MoviesResult(
                page = 1,
                results = MovieDbMapper.toViewObject(it)
            )
        }

    override fun delete(id: Long): Completable = db.delete(id)

    override fun getLiked(id: Long): Observable<MovieDbEntity> = db.get(id)

    override fun update(movie: MovieDbEntity): Completable = db.update(movie)
}
