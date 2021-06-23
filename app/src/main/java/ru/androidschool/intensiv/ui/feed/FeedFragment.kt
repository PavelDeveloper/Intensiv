package ru.androidschool.intensiv.ui.feed

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.network.entity.Movie
import ru.androidschool.intensiv.ui.view.MovieItem
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    openSearch(it.toString())
                    requireActivity().hideKeyboard()
                }
        )
    }

    private fun initObservers() {

        viewModel.isDownloading.observe(requireActivity(), Observer { progress_bar.isVisible = it })

        viewModel.playingMovies.observe(requireActivity(), Observer { movies ->
            val moviesList = listOf(
                MainCardContainer(
                    R.string.playing,
                    movies.distinct().map {
                        MovieItem(it) { movie ->
                            openMovieDetails(
                                movie
                            )
                        }
                    }.toList()
                )
            )
            adapter.apply { addAll(moviesList) }
        })

        viewModel.popularMovies.observe(requireActivity(), Observer { movie ->
            val popularMoviesList = listOf(
                MainCardContainer(
                    R.string.popular,
                    movie.distinct().map {
                        MovieItem(it) { movie ->
                            openMovieDetails(movie)
                        }
                    }.toList()
                )
            )
            adapter.apply { addAll(popularMoviesList) }
        })

        viewModel.upcomingMovies.observe(requireActivity(), Observer { movie ->
            val popularMoviesList = listOf(
                MainCardContainer(
                    R.string.upcoming,
                    movie.distinct().map {
                        MovieItem(it) { movie ->
                            openMovieDetails(movie)
                        }
                    }.toList()
                )
            )
            adapter.apply { addAll(popularMoviesList) }
        })
    }

    private fun openMovieDetails(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(KEY_MOVIE_ID, movie.id)
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
