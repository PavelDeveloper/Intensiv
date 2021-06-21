package ru.androidschool.intensiv.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.actors.ActorsRepositoryImpl
import ru.androidschool.intensiv.data.details.DetailsMovieRepositoryImpl
import ru.androidschool.intensiv.network.entity.Actor
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import timber.log.Timber

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
            DetailsMovieRepositoryImpl.getMovieDetails(id)
                .subscribe({ _movieDetails.value = it },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    private fun getActors(id: Int) {
        compositeDisposable.add(
            ActorsRepositoryImpl.getActors(id)
                .subscribe({ _actors.value = it.cast },
                    { e -> Timber.d(e.localizedMessage) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
