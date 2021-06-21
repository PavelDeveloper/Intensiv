package ru.androidschool.intensiv.data.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.network.entity.TvShowsResponse

interface TvShowRepository {
    val tvShows: Single<TvShowsResponse>
}
