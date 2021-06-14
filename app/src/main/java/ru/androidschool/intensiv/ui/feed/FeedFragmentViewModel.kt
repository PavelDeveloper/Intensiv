package ru.androidschool.intensiv.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.MovieRepository
import ru.androidschool.intensiv.network.entity.Movie
import timber.log.Timber

class FeedFragmentViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

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

    private fun getPlayingMovies() {
        compositeDisposable.add(
            MovieRepository.playingMovies
                .subscribe(
                    { response -> _playingMovies.value = response.results },
                    { e -> Timber.d(e.localizedMessage) },
                    { Timber.d("onComplete") }
                )
        )
    }

    private fun getPopularMovies() {
        compositeDisposable.add(
            MovieRepository.popularMovies
                .subscribe(
                    { response -> _popularMovies.value = response.results },
                    { e -> Timber.d(e.localizedMessage) },
                    { Timber.d("onComplete") }
                )
        )
    }

    private fun getUpcomingMovies() {
        compositeDisposable.add(
            MovieRepository.upcomingMovies
                .subscribe {
                    _upcomingMovies.value = it.results
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
