package ru.androidschool.intensiv.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.data.tvshows.TvShowRepository
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult

class TvShowsUseCase(private val repository: TvShowRepository) {

    suspend fun getTvShows(): TvShowsResult =
        repository.getTvShows()

    suspend fun getPagingTvShows(): Flow<PagingData<TvShow>> =
        repository.getPagingTvShows()
}
