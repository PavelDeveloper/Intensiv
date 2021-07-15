package ru.androidschool.intensiv.presentation.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_with_text_horizontal.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.utils.loadImage

class TvShowsPagingAdapter :
    PagingDataAdapter<TvShow, TvShowsPagingAdapter.TvShowViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_with_text_horizontal, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(content: TvShow) {
            itemView.title.text = content.originalName
            itemView.rating.rating = content.voteAverage.toFloat() / 2
            itemView.image_poster.loadImage(content.posterPath)
        }
    }
}
