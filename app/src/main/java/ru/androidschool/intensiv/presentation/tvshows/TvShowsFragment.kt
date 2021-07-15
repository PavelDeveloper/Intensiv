package ru.androidschool.intensiv.presentation.tvshows

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.tvshows.repository.PopularTvShowsRepository
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import ru.androidschool.intensiv.presentation.view.ShowItem
import timber.log.Timber

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment), TvShowsPresenter.TvShowsView {

    private val presenter: TvShowsPresenter by lazy {
        TvShowsPresenter(TvShowsUseCase(PopularTvShowsRepository))
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val pagingAdapter by lazy {
        TvShowsPagingAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        /**
         * решил реализовать как запрос на корутинах
         * так и пагинацию.
         * */

        /**
         * для получения только первой страницы использую этот метод
         *
         * */
        // presenter.getShows()

        /**
         * для пагинации этот + pagingAdapter для recycler_view
         * */
        presenter.getPagingTvShows()
        shows_recycler_view.adapter = pagingAdapter
    }

    override fun showMovies(tvShows: List<TvShow>) {
        val showsList = tvShows.map {
            ShowItem(it) { movie ->
                Timber.d("show's name ${movie.name}")
            }
        }.toList()
        shows_recycler_view.adapter = adapter.apply { addAll(showsList) }
    }

    override fun showPagingMovies(tvShow: PagingData<TvShow>) {
        lifecycleScope.launch {
            pagingAdapter.submitData(tvShow)
        }
    }

    override fun showLoading() {
        progress_bar.isVisible = true
    }

    override fun hideLoading() {
        progress_bar.isVisible = false
    }

    override fun showEmptyMovies() {
        showToast(R.string.empty_tv_show_list)
    }

    override fun showError() {
        showToast(R.string.error_tv_shows_getting)
    }

    private fun showToast(@StringRes message: Int) {
        Toast.makeText(requireContext(), getString(message), Toast.LENGTH_LONG).show()
    }
}
