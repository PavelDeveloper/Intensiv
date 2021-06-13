package ru.androidschool.intensiv.data

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import ru.androidschool.intensiv.network.entity.MoviesResponse

object MovieRepository {

    val playingMovies: Observable<MoviesResponse>
        get() = MovieApiClient.api.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    val popularMovies: Observable<MoviesResponse>
        get() = MovieApiClient.api.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    val upcomingMovies: Observable<MoviesResponse>
        get() = MovieApiClient.api.getUpcomingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getActors(id: Int): Observable<CreditsResponse> = MovieApiClient.api.getActors(movieId = id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getMovieDetails(id: Int): Observable<MovieDetailResponse> = MovieApiClient.api.getMovieDetail(movieId = id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


}