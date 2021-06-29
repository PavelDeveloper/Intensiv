package ru.androidschool.intensiv.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.data.movies.LikedMoviesRepository
import ru.androidschool.intensiv.domain.entity.Movie
import timber.log.Timber

class WatchlistViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

    init {
        fetch()
    }

    private fun fetch() {
        val disposable = LikedMoviesRepository.fetch()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                _movies.value = it.results
            }, { e ->
                Timber.e(e.localizedMessage)
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
