package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.movie_details_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.ui.feed.FeedFragment
import ru.androidschool.intensiv.utils.loadImage

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val viewModel: MovieDetailsViewModel by viewModels()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private var movieId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getInt(FeedFragment.KEY_MOVIE_ID)

        viewModel.getMovieDetail(requireNotNull(movieId))
        viewModel.getActors(requireNotNull(movieId))

        viewModel.movieDetails.observe(requireActivity(), Observer { details ->
            title_tv.text = details.title
            detail_rating.rating = details.voteAverage.toFloat() / 2
            description_tv.text = details.overview
            genre_name_tv.text = details.genres.first().name
            studio_name_tv.text = details.productionCompanies.first().name
            year_name_tv.text = details.releaseDate
            image_detail_preview.loadImage(details.posterPath)
        })

        viewModel.actors.observe(requireActivity(), Observer { actors ->
            val actorList = actors.map { ActorItem(it) }.toList()
            actors_recycler_view.adapter = adapter.apply { addAll(actorList) }
        })
    }
}
