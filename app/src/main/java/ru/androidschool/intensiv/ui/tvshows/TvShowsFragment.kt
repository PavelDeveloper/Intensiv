package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R
import timber.log.Timber

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private val viewModel: TvShowsViewModel by viewModels()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTvShows()

        viewModel.shows.observe(requireActivity(), Observer { tvShows ->

            val showsList = tvShows.map {
                ShowItem(it) { movie ->
                    Timber.d("show's name ${movie.name}")
                }
            }.toList()
            shows_recycler_view.adapter = adapter.apply { addAll(showsList) }
        })
    }
}
