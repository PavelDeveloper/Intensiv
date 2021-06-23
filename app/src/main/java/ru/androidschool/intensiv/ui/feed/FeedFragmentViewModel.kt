package ru.androidschool.intensiv.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import ru.androidschool.intensiv.data.movies.MovieType
import ru.androidschool.intensiv.data.movies.PlayingMovieRepository
import ru.androidschool.intensiv.data.movies.PopularMoviesRepository
import ru.androidschool.intensiv.data.movies.UpcomingRepository
import ru.androidschool.intensiv.network.entity.Movie
import ru.androidschool.intensiv.network.entity.MoviesResponse
import timber.log.Timber

class FeedFragmentViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _playingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val playingMovies: LiveData<List<Movie>> = _playingMovies

    private val _popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    private val _upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val upcomingMovies: LiveData<List<Movie>> = _upcomingMovies

    private val _isDownloading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        getMovies()
    }

    private fun getMovies() {
        compositeDisposable.add(
            Single
                .zip(
                    PlayingMovieRepository.getMovies(),
                    PopularMoviesRepository.getMovies(),
                    UpcomingRepository.getMovies(),
                    Function3<MoviesResponse, MoviesResponse, MoviesResponse, HashMap<MovieType, MoviesResponse>> { playing, popular, upcoming ->
                        hashMapOf(
                            MovieType.PLAYING to playing,
                            MovieType.POPULAR to popular,
                            MovieType.UPCOMING to upcoming
                        )
                    }
                )
                .doOnSubscribe { _isDownloading.value = true }
                .doFinally { _isDownloading.value = false }
                .subscribe({ hashMap ->
                    hashMap.keys.forEach {
                        when (it) {
                            MovieType.PLAYING -> _playingMovies.value = hashMap[it]?.results
                            MovieType.POPULAR -> _popularMovies.value = hashMap[it]?.results
                            MovieType.UPCOMING -> _upcomingMovies.value = hashMap[it]?.results
                        }
                    }
                },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
