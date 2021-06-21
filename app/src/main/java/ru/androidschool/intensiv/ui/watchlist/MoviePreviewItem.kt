package ru.androidschool.intensiv.ui.watchlist

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.mock.MovieMock
import ru.androidschool.intensiv.utils.loadImage

class MoviePreviewItem(
    private val content: MovieMock,
    private val onClick: (movie: MovieMock) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.image_preview.setOnClickListener {
            onClick.invoke(content)
        }
        // TODO Получать из модели
        viewHolder.image_preview.loadImage("https://www.kinopoisk.ru/images/film_big/1143242.jpg")
    }
}
