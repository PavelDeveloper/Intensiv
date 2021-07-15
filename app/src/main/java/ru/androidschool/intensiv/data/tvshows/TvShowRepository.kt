package ru.androidschool.intensiv.data.tvshows

import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult

interface TvShowRepository {
    suspend fun getTvShows(): TvShowsResult
}
