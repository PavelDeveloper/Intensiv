package ru.androidschool.intensiv.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.tvshows.repository.PopularTvShowsRepository
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import timber.log.Timber

class TvShowsViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _shows: MutableLiveData<List<TvShow>> = MutableLiveData()
    val shows: LiveData<List<TvShow>> = _shows

    private val _isDownloading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        getTvShows()
    }

    private fun getTvShows() {
        compositeDisposable.add(
            PopularTvShowsRepository.tvShows
                .doOnSubscribe { _isDownloading.value = true }
                .doFinally { _isDownloading.value = false }
                .subscribe({ _shows.value = it.results },
                    { e -> Timber.d(e.localizedMessage) }
                )
        )
    }
}
