package ru.androidschool.intensiv.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import ru.androidschool.intensiv.network.entity.MoviesResponse
import ru.androidschool.intensiv.network.entity.TvShowsResponse

interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Observable<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Observable<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Observable<MoviesResponse>

    @GET("tv/popular")
    fun getTvPopular(): Observable<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Observable<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getActors(@Path("movie_id") movieId: Int): Observable<CreditsResponse>

    @GET("search/movie")
    fun getSearchMovies(@Query("query") query: CharSequence): Observable<MoviesResponse>
}
