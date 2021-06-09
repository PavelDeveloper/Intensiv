package ru.androidschool.intensiv.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.TvShow
import ru.androidschool.intensiv.network.entity.TvShowsResponse
import timber.log.Timber

class TvShowsViewModel : ViewModel() {

    private val _shows: MutableLiveData<List<TvShow>> = MutableLiveData()
    val shows: LiveData<List<TvShow>> = _shows

    fun getTvShows() {
        MovieApiClient.api.getTvPopular()
            .enqueue(object : Callback<TvShowsResponse> {
                override fun onResponse(
                    call: Call<TvShowsResponse>,
                    response: Response<TvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _shows.postValue(it.results)
                        }
                    }
                }

                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                    Timber.e(t.localizedMessage)
                }
            })
    }
}
