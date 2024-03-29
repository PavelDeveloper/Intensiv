package ru.androidschool.intensiv.presentation.view

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text_horizontal.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.utils.loadImage

class MovieItemHorizontal(
    private val content: Movie,
    private val onClick: (movieNetworkEntity: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text_horizontal

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.title.text = content.title
        viewHolder.rating.rating = content.voteAverage.toFloat() / 2
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_poster.loadImage(content.posterPath)
    }
}
