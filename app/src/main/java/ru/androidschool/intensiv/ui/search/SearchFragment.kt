package ru.androidschool.intensiv.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.ui.feed.FeedFragment
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.ui.view.MovieItemHorizontal
import ru.androidschool.intensiv.utils.hideKeyboard
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels {
        SearchMovieViewModelFactory(
            arguments?.getString(
                KEY_SEARCH
            )
        )
    }

    private val compositeDisposable = CompositeDisposable()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        search_toolbar.setText(searchTerm)

        initSearchView()
        initObserver()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun initSearchView() {
        compositeDisposable.add(
            search_toolbar.onTextChangedObservable
                .filter { it.length > FeedFragment.MIN_LENGTH }
                .map { it.trim() }
                .debounce(FeedFragment.DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.clear()
                    viewModel.onSearchStarted(it.toString())
                    requireActivity().hideKeyboard()
                }
        )
    }

    private fun initObserver() {

        viewModel.isDownloading.observe(requireActivity(), Observer { progress_bar.isVisible = it })

        viewModel.searchMovies.observe(requireActivity(), Observer { result ->
            val searchedMoviesList = result.map {
                MovieItemHorizontal(it) { movie ->
                    Timber.d("movie's name ${movie.originalTitle}")
                }
            }
            movies_recycler_view.adapter = adapter.apply { addAll(searchedMoviesList) }
        })
    }
}
