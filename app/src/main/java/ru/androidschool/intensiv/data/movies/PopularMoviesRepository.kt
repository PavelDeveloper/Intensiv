package ru.androidschool.intensiv.data.movies

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.androidschool.intensiv.MovieFinderApp
import ru.androidschool.intensiv.data.CashProvider
import ru.androidschool.intensiv.data.RepositoryAccess
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity
import ru.androidschool.intensiv.network.MovieApiClient

object PopularMoviesRepository : CashProvider<MoviesDomainEntity>(), MovieRepository {

    private val db = MovieFinderApp.instance?.database

    override fun fetch(): Flowable<MoviesDomainEntity> = getFlowable(RepositoryAccess.OFFLINE_FIRST)

    override fun save(movies: List<Movie>): Completable? {
        return db?.insertAll(
            movies = movies.map { it.toDomain().copy(movieType = MovieType.POPULAR) }
        )
    }

    override fun deleteAll(): Completable? =
        db?.delete(MovieType.POPULAR, false)

    override fun delete(id: Long): Completable? = null

    override fun createRemoteFlowable(): Flowable<MoviesDomainEntity> =
        MovieApiClient.api.getPopularMovies().map { it.toDomain() }

    override fun createOfflineFlowable(): Flowable<MoviesDomainEntity> {
        return db?.get(MovieType.POPULAR)?.map {
            if (it.isNotEmpty()) {
                MoviesDomainEntity(
                    page = 1,
                    results = it.map { item -> item.toDomain() })
            } else {
                throw Exception()
            }
        }!!
    }
}
