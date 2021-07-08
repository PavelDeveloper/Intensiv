package ru.androidschool.intensiv.presentation.tvshows

import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import ru.androidschool.intensiv.presentation.base.BasePresenter

class TvShowsPresenter(private val useCase: TvShowsUseCase) :
    BasePresenter<TvShowsPresenter.TvShowsView>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getShows() {
        compositeDisposable.add(
            useCase.tvShows
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .subscribe({
                    if (it.results.isEmpty()) view?.showEmptyMovies() else
                        view?.showMovies(it.results)
                }, {
                    view?.showError()
                })
        )
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    interface TvShowsView {
        fun showMovies(tvShows: List<TvShow>)
        fun showLoading()
        fun hideLoading()
        fun showEmptyMovies()
        fun showError()
    }
}
