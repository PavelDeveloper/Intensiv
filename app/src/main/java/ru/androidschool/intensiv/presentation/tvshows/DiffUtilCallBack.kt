package ru.androidschool.intensiv.presentation.tvshows

import androidx.recyclerview.widget.DiffUtil
import ru.androidschool.intensiv.data.tvshows.vo.TvShow

class DiffUtilCallBack : DiffUtil.ItemCallback<TvShow>() {
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem == newItem
    }
}
