package ru.androidschool.intensiv.domain.usecase

import ru.androidschool.intensiv.data.tvshows.TvShowRepository
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult

class TvShowsUseCase(private val repository: TvShowRepository) {

    suspend fun getTvShows(): TvShowsResult =
        repository.getTvShows()
}
