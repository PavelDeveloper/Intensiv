package ru.androidschool.intensiv.network

import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import ru.androidschool.intensiv.network.entity.MoviesResponse
import ru.androidschool.intensiv.network.entity.TvShowsResponse

interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Flowable<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Flowable<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Flowable<MoviesResponse>

    @GET("tv/popular")
    fun getTvPopular(): Single<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Long): Single<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getActors(@Path("movie_id") movieId: Long): Single<CreditsResponse>

    @GET("search/movie")
    fun getSearchMovies(@Query("query") query: CharSequence): Single<MoviesResponse>
}
