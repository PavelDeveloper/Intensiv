package ru.androidschool.intensiv.presentation.tvshows

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import ru.androidschool.intensiv.presentation.base.BasePresenter

class TvShowsPresenter(private val useCase: TvShowsUseCase) :
    BasePresenter<TvShowsPresenter.TvShowsView>() {

    private val job = Job()
    private val scope = CoroutineScope(job)

    fun getShows() {
        view?.showLoading()
        scope.launch(Dispatchers.Main) {
            val data = useCase.getTvShows()
            view?.hideLoading()
            if (data.results.isNotEmpty()) {
                view?.showMovies(data.results)
            } else {
                view?.showError()
            }
        }
    }

    fun getPagingTvShows() {
        scope.launch(Dispatchers.Main) {
            view?.hideLoading()
            useCase.getPagingTvShows().collectLatest {
                view?.showPagingMovies(it)
            }
        }
    }

    override fun detachView() {
        job.cancel()
    }

    interface TvShowsView {
        fun showMovies(tvShows: List<TvShow>)
        fun showPagingMovies(tvShow: PagingData<TvShow>)
        fun showLoading()
        fun hideLoading()
        fun showEmptyMovies()
        fun showError()
    }
}
