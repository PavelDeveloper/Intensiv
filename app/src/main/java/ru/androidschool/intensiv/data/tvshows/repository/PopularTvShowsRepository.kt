package ru.androidschool.intensiv.data.tvshows.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.data.tvshows.TvShowRepository
import ru.androidschool.intensiv.data.tvshows.mappers.TvShowsResultMapper
import ru.androidschool.intensiv.data.tvshows.paging.TvShowsPagingSource
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.utils.PAGE_ELEMENTS_COUNT

object PopularTvShowsRepository : TvShowRepository {

    override suspend fun getTvShows(): TvShowsResult {
        return TvShowsResultMapper.toValueObject(MovieApiClient.apiClient.getTvPopular())
    }

    override suspend fun getPagingTvShows(): Flow<PagingData<TvShow>> {
        return Pager(PagingConfig(PAGE_ELEMENTS_COUNT, enablePlaceholders = false)) { TvShowsPagingSource() }.flow
    }
}
