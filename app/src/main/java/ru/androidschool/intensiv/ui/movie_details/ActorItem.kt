package ru.androidschool.intensiv.ui.movie_details

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text_circle.*
import kotlinx.android.synthetic.main.item_with_text_horizontal.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Actor
import ru.androidschool.intensiv.utils.CircleTransform

class ActorItem(
    private val content: Actor
) : Item() {

    override fun getLayout() = R.layout.item_with_text_circle

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.name_tv.text = content.name.replace(" ", "\n")

        Picasso.get()
            .load(content.avatarUrl)
            .transform(CircleTransform())
            .into(viewHolder.actor_avatar)
    }
}
