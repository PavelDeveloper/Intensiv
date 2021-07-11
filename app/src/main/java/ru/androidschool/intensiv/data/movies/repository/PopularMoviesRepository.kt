package ru.androidschool.intensiv.data.movies.repository

import io.reactivex.Completable
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.CashProvider
import ru.androidschool.intensiv.data.RepositoryAccess
import ru.androidschool.intensiv.data.db.AppDatabase
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.data.movies.mappers.MovieDbMapper
import ru.androidschool.intensiv.data.movies.mappers.MovieResultMapper
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.network.MovieApiClient

object PopularMoviesRepository : CashProvider<MoviesResult>(), MovieRepository, KoinComponent {

    private val db: AppDatabase by inject()

    override fun fetch(): Observable<MoviesResult> = getObservable(RepositoryAccess.OFFLINE_FIRST)

    override fun save(movies: List<Movie>): Completable {
        return db.movieDao().insertAll(
                movies = movies.map { MovieDbMapper.toDbObject(it).copy(movieType = MovieType.POPULAR) }
            )
    }

    override fun deleteAll(): Completable =
        db.movieDao().delete(MovieType.POPULAR, false)

    override fun createRemoteObservable(): Observable<MoviesResult> =
        MovieApiClient.apiClient.getPopularMovies().map { MovieResultMapper.toValueObject(it) }

    override fun createOfflineObservable(): Observable<MoviesResult> {
        return db.movieDao().get(MovieType.POPULAR).map {
            if (it.isNotEmpty()) {
                MoviesResult(
                    page = 1,
                    results = MovieDbMapper.toViewObject(it)
                )
            } else {
                throw Exception()
            }
        }
    }
}
