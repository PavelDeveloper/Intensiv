package ru.androidschool.intensiv.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.TvShowsRepository
import ru.androidschool.intensiv.network.entity.TvShow

class TvShowsViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _shows: MutableLiveData<List<TvShow>> = MutableLiveData()
    val shows: LiveData<List<TvShow>> = _shows

    init {
        getTvShows()
    }

    private fun getTvShows() {
        compositeDisposable.add(
            TvShowsRepository.tvShows.subscribe { _shows.value = it.results }
        )
    }
}
