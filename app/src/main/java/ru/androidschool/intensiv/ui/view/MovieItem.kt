package ru.androidschool.intensiv.ui.view

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.domain.entity.Movie
import ru.androidschool.intensiv.utils.loadImage

class MovieItem(
    private val content: Movie,
    private val onClick: (movieNetworkEntity: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.movie_rating.rating = content.voteAverage.toFloat() / 2
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_preview.loadImage(content.posterPath)
    }
}
