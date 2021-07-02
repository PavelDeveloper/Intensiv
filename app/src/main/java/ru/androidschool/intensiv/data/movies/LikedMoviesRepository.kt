package ru.androidschool.intensiv.data.movies

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.MovieFinderApp
import ru.androidschool.intensiv.data.movies.entity.LikeRepository
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity

object LikedMoviesRepository : LikeRepository {

    private val db = MovieFinderApp.instance.database.movieDao()

    override fun fetch(): Observable<MoviesDomainEntity> =
        db.getLiked().map {
            MoviesDomainEntity(
                page = 1,
                results = it.map { item -> item.toDomain() }
            )
        }

    override fun delete(id: Long): Completable = db.delete(id)

    override fun getLiked(id: Long): Observable<MovieDbEntity> = db.get(id)

    override fun update(movie: MovieDbEntity): Completable = db.update(movie)
}
