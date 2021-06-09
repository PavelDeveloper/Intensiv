package ru.androidschool.intensiv.ui.movie_details

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_with_text_circle.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.network.entity.Actor
import ru.androidschool.intensiv.utils.loadTransformationImage

class ActorItem(
    private val content: Actor
) : Item() {

    override fun getLayout() = R.layout.item_with_text_circle

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.name_tv.text = content.name.replace(" ", "\n")
        viewHolder.actor_avatar.loadTransformationImage(content.profilePath, CropCircleTransformation())
    }
}
