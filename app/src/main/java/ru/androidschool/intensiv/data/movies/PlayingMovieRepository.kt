package ru.androidschool.intensiv.data.movies

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.MovieFinderApp
import ru.androidschool.intensiv.data.CashProvider
import ru.androidschool.intensiv.data.RepositoryAccess
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity
import ru.androidschool.intensiv.network.MovieApiClient

object PlayingMovieRepository : CashProvider<MoviesDomainEntity>(), MovieRepository {

    private val db = MovieFinderApp.instance.database.movieDao()

    override fun fetch(): Observable<MoviesDomainEntity> = getObservable(RepositoryAccess.OFFLINE_FIRST)

    override fun save(movies: List<Movie>): Completable {
        return db.insertAll(
            movies = movies.map { it.toDomain().copy(movieType = MovieType.PLAYING) }
        )
    }

    override fun deleteAll(): Completable {
        return db.delete(MovieType.PLAYING, false)
    }

    override fun createRemoteObservable(): Observable<MoviesDomainEntity> =
        MovieApiClient.api.getNowPlayingMovies().map { it.toDomain() }

    public override fun createOfflineObservable(): Observable<MoviesDomainEntity> {
        return db.get(MovieType.PLAYING)
            .map {
                if (it.isNotEmpty()) {
                    MoviesDomainEntity(
                        page = 1,
                        results = it.map { item -> item.toDomain() })
                } else {
                    throw Exception()
                }
            }
    }
}
