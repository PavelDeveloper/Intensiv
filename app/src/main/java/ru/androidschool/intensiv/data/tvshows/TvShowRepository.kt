package ru.androidschool.intensiv.data.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult

interface TvShowRepository {
    val tvShows: Single<TvShowsResult>
}
