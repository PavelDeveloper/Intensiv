package ru.androidschool.intensiv.data.tvshows.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.tvshows.TvShowRepository
import ru.androidschool.intensiv.data.tvshows.mappers.TvShowsResultMapper
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult
import ru.androidschool.intensiv.network.MovieApiClient

object PopularTvShowsRepository : TvShowRepository {

    override val tvShows: Single<TvShowsResult>
        get() = MovieApiClient.apiClient.getTvPopular().map { TvShowsResultMapper.toValueObject(it) }
}
