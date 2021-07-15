package ru.androidschool.intensiv.data.tvshows.repository

import ru.androidschool.intensiv.data.tvshows.TvShowRepository
import ru.androidschool.intensiv.data.tvshows.mappers.TvShowsResultMapper
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult
import ru.androidschool.intensiv.network.MovieApiClient

object PopularTvShowsRepository : TvShowRepository {

    override suspend fun getTvShows(): TvShowsResult {
        return TvShowsResultMapper.toValueObject(MovieApiClient.apiClient.getTvPopular())
    }
}
