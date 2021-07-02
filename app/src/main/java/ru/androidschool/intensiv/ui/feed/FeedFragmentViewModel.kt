package ru.androidschool.intensiv.ui.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import ru.androidschool.intensiv.data.movies.PlayingMovieRepository
import ru.androidschool.intensiv.data.movies.PopularMoviesRepository
import ru.androidschool.intensiv.data.movies.UpcomingRepository
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity
import ru.androidschool.intensiv.utils.on
import timber.log.Timber

class FeedFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _movies: MutableLiveData<HashMap<MovieType, MoviesDomainEntity>> = MutableLiveData()
    val movies: LiveData<HashMap<MovieType, MoviesDomainEntity>> = _movies

    private val _isDownloading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        getMovies()
    }

    private fun getMovies() {

        compositeDisposable.add(
            Observable
                .zip(
                    PlayingMovieRepository.fetch(),
                    PopularMoviesRepository.fetch(),
                    UpcomingRepository.fetch(),
                    Function3<MoviesDomainEntity, MoviesDomainEntity, MoviesDomainEntity,
                            HashMap<MovieType, MoviesDomainEntity>> { playing, popular, upcoming ->
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
                    _movies.value = hashMap
                    if (hashMap.values.first().results.isNotEmpty()) {
                        _isDownloading.value = false
                        saveMoviesToDb(hashMap)
                    }
                },
                    { e ->
                        _isDownloading.value = false
                        Timber.d(e.localizedMessage)
                    }
                )
        )
    }

    private fun saveMoviesToDb(hashMovies: HashMap<MovieType, MoviesDomainEntity>) {
        Observable.concat(
            PlayingMovieRepository.save(hashMovies[MovieType.PLAYING]!!.results)
                .toObservable<Unit>(),
            PopularMoviesRepository.save(hashMovies[MovieType.POPULAR]!!.results)
                .toObservable<Unit>(),
            UpcomingRepository.save(hashMovies[MovieType.UPCOMING]!!.results).toObservable<Unit>()
        )
            .on()
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
