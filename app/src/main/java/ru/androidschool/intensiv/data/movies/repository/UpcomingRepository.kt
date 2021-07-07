package ru.androidschool.intensiv.data.movies.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.MovieFinderApp
import ru.androidschool.intensiv.data.CashProvider
import ru.androidschool.intensiv.data.RepositoryAccess
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.data.movies.mappers.MovieDbMapper
import ru.androidschool.intensiv.data.movies.mappers.MovieResultMapper
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.network.MovieApiClient

object UpcomingRepository : CashProvider<MoviesResult>(), MovieRepository {

    private val db = MovieFinderApp.instance.database.movieDao()

    override fun fetch(): Observable<MoviesResult> =
        getObservable(RepositoryAccess.OFFLINE_FIRST)

    override fun save(movies: List<Movie>): Completable {
        return db.insertAll(
            movies = movies.map { MovieDbMapper.toDbObject(it).copy(movieType = MovieType.UPCOMING) }
        )
    }

    override fun deleteAll(): Completable =
        db.delete(MovieType.UPCOMING, false)

    override fun createRemoteObservable(): Observable<MoviesResult> =
        MovieApiClient.api.getUpcomingMovies().map { MovieResultMapper.toValueObject(it) }

    public override fun createOfflineObservable(): Observable<MoviesResult> {
        return db.get(MovieType.UPCOMING).map {
            if (it.isNotEmpty()) {
                MoviesResult(
                    page = 1,
                    results = it.map { item -> MovieDbMapper.toValueObject(item) })
            } else {
                throw Exception()
            }
        }
    }
}