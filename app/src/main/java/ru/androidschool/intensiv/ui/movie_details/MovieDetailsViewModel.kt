package ru.androidschool.intensiv.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.Actor
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import timber.log.Timber

class MovieDetailsViewModel(movieId: Int?) : ViewModel() {

    init {
        movieId?.let {
            getMovieDetail(it)
            getActors(it)
        }
    }

    private val _movieDetails: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val movieDetails: LiveData<MovieDetailResponse> = _movieDetails

    private val _actors: MutableLiveData<List<Actor>> = MutableLiveData()
    val actors: LiveData<List<Actor>> = _actors

    private fun getMovieDetail(id: Int) {
        MovieApiClient.api.getMovieDetail(movieId = id)
            .enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _movieDetails.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Timber.e(t.localizedMessage)
                }
            })
    }

    private fun getActors(id: Int) {
        MovieApiClient.api.getActors(movieId = id)
            .enqueue(object : Callback<CreditsResponse> {
                override fun onResponse(
                    call: Call<CreditsResponse>,
                    response: Response<CreditsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _actors.value = it.cast
                        }
                    }
                }

                override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                    Timber.e(t.localizedMessage)
                }
            })
    }
}
