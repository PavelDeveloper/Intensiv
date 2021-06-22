package ru.androidschool.intensiv.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.movies.PlayingMovieRepository
import ru.androidschool.intensiv.data.movies.PopularMoviesRepository
import ru.androidschool.intensiv.data.movies.UpcomingRepository
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
            PlayingMovieRepository.getMovies()
                .subscribe(
                    { response -> _playingMovies.value = response.results },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    private fun getPopularMovies() {
        compositeDisposable.add(
            PopularMoviesRepository.getMovies()
                .subscribe(
                    { response -> _popularMovies.value = response.results },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    private fun getUpcomingMovies() {
        compositeDisposable.add(
            UpcomingRepository.getMovies()
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
