package ru.androidschool.intensiv.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.MovieRepository
import ru.androidschool.intensiv.network.entity.Actor
import ru.androidschool.intensiv.network.entity.MovieDetailResponse

class MovieDetailsViewModel(movieId: Int?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

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
        compositeDisposable.add(
            MovieRepository.getMovieDetails(id)
                .subscribe { _movieDetails.value = it }
        )
    }

    private fun getActors(id: Int) {
        compositeDisposable.add(
            MovieRepository.getActors(id)
                .subscribe { _actors.value = it.cast }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
