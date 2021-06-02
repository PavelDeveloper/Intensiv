package ru.androidschool.intensiv.ui.tvshows

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_card_shows.*
import ru.androidschool.intensiv.R

class CardContainer(
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.item_card_shows

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.items_container_show.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
