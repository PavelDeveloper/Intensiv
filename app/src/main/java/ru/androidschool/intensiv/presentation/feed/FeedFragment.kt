package ru.androidschool.intensiv.presentation.feed

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.presentation.view.MovieItem
import ru.androidschool.intensiv.utils.applySchedulers
import ru.androidschool.intensiv.utils.hideKeyboard
import java.util.concurrent.TimeUnit

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val viewModel: FeedFragmentViewModel by viewModels()
    private val compositeDisposable = CompositeDisposable()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies_recycler_view.adapter = adapter
        initObservers()

        compositeDisposable.add(
            search_toolbar
                .onTextChangedObservable
                .filter { it.length > MIN_LENGTH }
                .map { it.trim() }
                .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .applySchedulers()
                .subscribe {
                    openSearch(it.toString())
                    requireActivity().hideKeyboard()
                }
        )
    }

    private fun initObservers() {
        viewModel.isDownloading.observe(
            requireActivity(),
            Observer { progress_bar?.isVisible = it })
        viewModel.movies.observe(requireActivity(), Observer { renderItems(it) })
    }

    private fun renderItems(map: HashMap<MovieType, MoviesResult>) {
        map.keys.forEach { key ->
            when (key) {
                MovieType.PLAYING -> {
                    map[key]?.results?.let {
                        initRecycler(it, R.string.playing)
                    }
                }
                MovieType.UPCOMING -> {
                    map[key]?.results?.let {
                        initRecycler(it, R.string.upcoming)
                    }
                }
                MovieType.POPULAR -> {
                    map[key]?.results?.let {
                        initRecycler(it, R.string.popular)
                    }
                }
                else -> MovieType.DEFAULT
            }
        }
    }

    private fun initRecycler(movies: List<Movie>, @StringRes title: Int) {
        val moviesList = listOf(
            MainCardContainer(
                title,
                movies.distinct().map {
                    MovieItem(it) { movie ->
                        openMovieDetails(movie)
                    }
                }.toList()
            )
        )
        adapter.apply { addAll(moviesList) }
    }

    private fun openMovieDetails(movieNetworkEntity: Movie) {
        val bundle = Bundle()
        bundle.putLong(KEY_MOVIE_ID, movieNetworkEntity.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        search_toolbar.clear()
        adapter.clear()
        compositeDisposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_MOVIE_ID = "movie_id"
        const val KEY_SEARCH = "search"
        const val DEBOUNCE_TIME = 500L
    }
}
