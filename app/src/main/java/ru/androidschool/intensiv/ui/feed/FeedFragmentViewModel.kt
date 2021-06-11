package ru.androidschool.intensiv.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.Movie
import ru.androidschool.intensiv.network.entity.MoviesResponse
import timber.log.Timber

class FeedFragmentViewModel : ViewModel() {

    init {
        getPlayingMovies()
        getPopularMovies()
        getUpcomingMovies()
    }

    private val _playingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val playingMovies: LiveData<List<Movie>> = _playingMovies

    private val _popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    private val _upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val upcomingMovies: LiveData<List<Movie>> = _upcomingMovies

    fun getPlayingMovies() {
        val getMovies = MovieApiClient.api.getNowPlayingMovies()
        getMovies.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _playingMovies.value = it.results
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.localizedMessage)
            }
        })
    }

    fun getPopularMovies() {
        val getMovies = MovieApiClient.api.getPopularMovies()
        getMovies.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _popularMovies.value = it.results
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.localizedMessage)
            }
        })
    }

    fun getUpcomingMovies() {
        val getMovies = MovieApiClient.api.getUpcomingMovies()
        getMovies.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _upcomingMovies.value = it.results
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.localizedMessage)
            }
        })
    }
}
