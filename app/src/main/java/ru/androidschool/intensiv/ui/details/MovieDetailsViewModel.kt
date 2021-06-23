package ru.androidschool.intensiv.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import ru.androidschool.intensiv.data.actors.MovieActorsRepository
import ru.androidschool.intensiv.data.details.DetailsMovieRepository
import ru.androidschool.intensiv.domain.entity.MovieDetails
import ru.androidschool.intensiv.network.entity.Actor
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import timber.log.Timber

class MovieDetailsViewModel(movieId: Int?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movieDetails: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val movieDetails: LiveData<MovieDetailResponse> = _movieDetails

    private val _actors: MutableLiveData<List<Actor>> = MutableLiveData()
    val actors: LiveData<List<Actor>> = _actors

    private val _isDownloading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        movieId?.let {
            getMovieData(it)
        }
    }

    private fun getMovieData(id: Int) {
        compositeDisposable.add(
            Single.zip(
                DetailsMovieRepository.getDetails(id),
                MovieActorsRepository.getActors(id),
                BiFunction<MovieDetailResponse, CreditsResponse, MovieDetails> { details, actors ->
                    MovieDetails(
                        movieInfo = details,
                        actors = actors
                    )
                }
            )
                .doOnSubscribe { _isDownloading.value = true }
                .doFinally { _isDownloading.value = false }
                .subscribe({ movieDetails ->
                    _movieDetails.value = movieDetails.movieInfo
                    _actors.value = movieDetails.actors.cast
                },
                    { e -> Timber.d(e.localizedMessage) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
