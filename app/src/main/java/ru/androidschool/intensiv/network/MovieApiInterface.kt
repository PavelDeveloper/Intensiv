package ru.androidschool.intensiv.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import ru.androidschool.intensiv.network.entity.MoviesResponse
import ru.androidschool.intensiv.network.entity.TvShowsResponse

interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Call<MoviesResponse>

    @GET("tv/popular")
    fun getTvPopular(): Call<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Call<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getActors(@Path("movie_id") movieId: Int): Call<CreditsResponse>
}
