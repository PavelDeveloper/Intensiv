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

object PlayingMovieRepository : CashProvider<MoviesResult>(), MovieRepository {

    private val db = MovieFinderApp.instance.database.movieDao()

    override fun fetch(): Observable<MoviesResult> = getObservable(RepositoryAccess.OFFLINE_FIRST)

    override fun save(movies: List<Movie>): Completable {
        return db.insertAll(
            movies = movies.map { MovieDbMapper.toDbObject(it).copy(movieType = MovieType.PLAYING) }
        )
    }

    override fun deleteAll(): Completable {
        return db.delete(MovieType.PLAYING, false)
    }

    override fun createRemoteObservable(): Observable<MoviesResult> =
        MovieApiClient.api.getNowPlayingMovies().map { MovieResultMapper.toValueObject(it) }

    public override fun createOfflineObservable(): Observable<MoviesResult> {
        return db.get(MovieType.PLAYING)
            .map {
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
