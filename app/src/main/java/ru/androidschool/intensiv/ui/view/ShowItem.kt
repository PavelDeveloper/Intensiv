package ru.androidschool.intensiv.ui.view

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text_horizontal.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.network.entity.TvShow
import ru.androidschool.intensiv.utils.loadImage

class ShowItem(
    private val content: TvShow,
    private val onClick: (movie: TvShow) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text_horizontal

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.title.text = content.originalName
        viewHolder.rating.rating = content.voteAverage.toFloat() / 2
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_poster.loadImage(content.posterPath)
    }
}
