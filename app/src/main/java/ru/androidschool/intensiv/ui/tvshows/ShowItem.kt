package ru.androidschool.intensiv.ui.tvshows

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text_horizontal.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Show
import ru.androidschool.intensiv.utils.loadImage

class ShowItem(
    private val content: Show,
    private val onClick: (movie: Show) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text_horizontal

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.title.text = content.title
        viewHolder.show_rating.rating = content.voteAverage
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_poster.loadImage(content.posterUrl)
    }
}
