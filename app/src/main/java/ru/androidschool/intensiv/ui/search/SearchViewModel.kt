package ru.androidschool.intensiv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.search.SearchMoviesRepositoryImpl
import ru.androidschool.intensiv.network.entity.Movie
import timber.log.Timber

class SearchViewModel(query: CharSequence?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val searchMovies: LiveData<List<Movie>> = _searchMovies

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
            SearchMoviesRepositoryImpl.getSearchMovies(query).subscribe(
                { _searchMovies.value = it.results },
                { e -> Timber.d(e.localizedMessage) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
