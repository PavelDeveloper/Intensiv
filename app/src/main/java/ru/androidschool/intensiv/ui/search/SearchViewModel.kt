package ru.androidschool.intensiv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.search.SearchMoviesRepository
import ru.androidschool.intensiv.network.entity.MovieNetworkDataEntity
import timber.log.Timber

class SearchViewModel(query: CharSequence?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchMovies: MutableLiveData<List<MovieNetworkDataEntity>> = MutableLiveData()
    val searchMovies: LiveData<List<MovieNetworkDataEntity>> = _searchMovies

    private val _isDownloading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        query?.let {
            getSearchMovies(it)
        }
    }

    fun onSearchStarted(query: CharSequence) {
        getSearchMovies(query)
    }

    private fun getSearchMovies(query: CharSequence) {
        compositeDisposable.add(
            SearchMoviesRepository.getSearchMovies(query)
                .doOnSubscribe { _isDownloading.value = true }
                .doFinally { _isDownloading.value = false }
                .subscribe(
                    { _searchMovies.value = it.results },
                    { e -> Timber.d(e.localizedMessage) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
