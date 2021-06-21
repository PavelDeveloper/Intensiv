package ru.androidschool.intensiv.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.tvshows.TvShowsRepositoryImpl
import ru.androidschool.intensiv.network.entity.TvShow
import timber.log.Timber

class TvShowsViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _shows: MutableLiveData<List<TvShow>> = MutableLiveData()
    val shows: LiveData<List<TvShow>> = _shows

    init {
        getTvShows()
    }

    private fun getTvShows() {
        compositeDisposable.add(
            TvShowsRepositoryImpl.tvShows.subscribe(
                { _shows.value = it.results },
                { e -> Timber.d(e.localizedMessage) }
            )
        )
    }
}
