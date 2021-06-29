package ru.androidschool.intensiv.ui.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import ru.androidschool.intensiv.data.movies.PlayingMovieRepository
import ru.androidschool.intensiv.data.movies.PopularMoviesRepository
import ru.androidschool.intensiv.data.movies.UpcomingRepository
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.entity.MoviesDomainEntity
import ru.androidschool.intensiv.utils.updateDataDb
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

    /*    PlayingMovieRepository.fetch().subscribe({
            Timber.d("PlayingMovieRepository = ${it.results}")
        },
            { e ->
                Timber.e("PlayingMovieRepository = ${e.localizedMessage}")
            }
        )*/

        compositeDisposable.add(
            Flowable
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
                    Timber.d("MOVIES h = $hashMap")
                    _movies.value = hashMap
                    if (hashMap.values.first().results.isNotEmpty()) {
                        saveMoviesToDb(hashMap)
                    }
                },
                    { e ->
                        Timber.d(e.localizedMessage)
                    }
                )
        )
    }

    private fun saveMoviesToDb(hashMovies: HashMap<MovieType, MoviesDomainEntity>) {
        hashMovies[MovieType.PLAYING]?.let {
            PlayingMovieRepository.deleteAll()
                .updateDataDb { PlayingMovieRepository.save(it.results) }
        }
        hashMovies[MovieType.POPULAR]?.let {
            PopularMoviesRepository.deleteAll()
                .updateDataDb { PopularMoviesRepository.save(it.results) }
        }
        hashMovies[MovieType.UPCOMING]?.let {
            UpcomingRepository.deleteAll()
                .updateDataDb { UpcomingRepository.save(it.results) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
