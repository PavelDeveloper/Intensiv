package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.tvshows.TvShowRepository
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult
import ru.androidschool.intensiv.utils.applySchedulers

class TvShowsUseCase(repository: TvShowRepository) {
    val tvShows: Single<TvShowsResult> =
        repository.tvShows.applySchedulers()
}
