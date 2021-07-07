package ru.androidschool.intensiv.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.data.actors.dto.CreditsResponseDto
import ru.androidschool.intensiv.data.details.dto.MovieDetailInfoResponseDto
import ru.androidschool.intensiv.data.movies.dto.MoviesResponseDto
import ru.androidschool.intensiv.data.tvshows.dto.TvShowsResponseDto

interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Observable<MoviesResponseDto>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Observable<MoviesResponseDto>

    @GET("movie/popular")
    fun getPopularMovies(): Observable<MoviesResponseDto>

    @GET("tv/popular")
    fun getTvPopular(): Single<TvShowsResponseDto>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Long): Single<MovieDetailInfoResponseDto>

    @GET("movie/{movie_id}/credits")
    fun getActors(@Path("movie_id") movieId: Long): Single<CreditsResponseDto>

    @GET("search/movie")
    fun getSearchMovies(@Query("query") query: CharSequence): Single<MoviesResponseDto>
}
