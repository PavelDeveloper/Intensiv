package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.movie_details_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MockRepository.getMovieDetail
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.ui.feed.FeedFragment
import ru.androidschool.intensiv.utils.loadImage

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private var title: String? = null
    private var movie: Movie = getMovieDetail()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = arguments?.getString(FeedFragment.KEY_TITLE, "")

        title_tv.text = movie.title ?: title
        detail_rating.rating = movie.rating
        description_tv.text = movie.description
        genre_name_tv.text = movie.genre
        studio_name_tv.text = movie.studio
        year_name_tv.text = movie.year
        image_detail_preview.loadImage(movie.previewUrl)

        val actorList = movie.actors.map { ActorItem(it) }.toList()
        actors_recycler_view.adapter = adapter.apply { addAll(actorList) }
    }
}
