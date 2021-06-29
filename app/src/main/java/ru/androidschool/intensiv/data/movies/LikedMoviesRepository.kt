package ru.androidschool.intensiv.data.movies

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.androidschool.intensiv.MovieFinderApp
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity

object LikedMoviesRepository : MovieRepository {

    private val db = MovieFinderApp.instance?.database

    override fun fetch(): Flowable<MoviesDomainEntity>? =
        db?.getLiked()?.map {
            MoviesDomainEntity(
                page = 1,
                results = it.map { item -> item.toDomain() }
            )
        }

    override fun save(movies: List<Movie>): Completable? {
        return db?.insertAll(
            movies = movies.map { it.toDomain().copy(movieType = MovieType.POPULAR) }
        )
    }

    override fun deleteAll(): Completable? = null

    override fun delete(id: Long): Completable? = db?.delete(id)

    fun getLiked(id: Long): Flowable<MovieDbEntity>? = db?.get(id)

    fun update(movie: MovieDbEntity): Completable? = db?.update(movie)
}
