package ru.androidschool.intensiv.data.tvshows

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult

interface TvShowRepository {
    suspend fun getTvShows(): TvShowsResult
    suspend fun getPagingTvShows(): Flow<PagingData<TvShow>>
}
