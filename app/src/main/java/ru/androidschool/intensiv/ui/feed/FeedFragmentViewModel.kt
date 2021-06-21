package ru.androidschool.intensiv.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.movies.PlayingMovieRepositoryImpl
import ru.androidschool.intensiv.data.movies.PopularMoviesRepositoryImpl
import ru.androidschool.intensiv.data.movies.UpcomingRepositoryImpl
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
            PlayingMovieRepositoryImpl.getMovies()
                .subscribe(
                    { response -> _playingMovies.value = response.results },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    private fun getPopularMovies() {
        compositeDisposable.add(
            PopularMoviesRepositoryImpl.getMovies()
                .subscribe(
                    { response -> _popularMovies.value = response.results },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    private fun getUpcomingMovies() {
        compositeDisposable.add(
            UpcomingRepositoryImpl.getMovies()
                .subscribe(
                    { _upcomingMovies.value = it.results },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
