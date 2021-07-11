package ru.androidschool.intensiv.presentation.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.domain.usecase.LikeUseCase
import ru.androidschool.intensiv.utils.applySchedulers
import timber.log.Timber

class WatchlistViewModel : ViewModel(), KoinComponent {

    private val likeUseCase: LikeUseCase by inject()
    private val compositeDisposable = CompositeDisposable()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

    init {
        fetch()
    }

    private fun fetch() {
        val disposable = likeUseCase.fetchMovies()
            .applySchedulers()
            .subscribe({
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
