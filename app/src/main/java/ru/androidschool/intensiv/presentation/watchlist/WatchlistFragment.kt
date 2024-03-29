package ru.androidschool.intensiv.presentation.watchlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_watchlist.*
import ru.androidschool.intensiv.R

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private val viewModel: WatchlistViewModel by viewModels()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies_recycler_view.layoutManager = GridLayoutManager(context, 4)
        movies_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        viewModel.movies.observe(requireActivity(), Observer { movies ->
            val moviesList =
                movies.map {
                    MoviePreviewItem(
                        it
                    ) { movie -> }
                }.toList()

            movies_recycler_view.adapter = adapter.apply { addAll(moviesList) }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}
