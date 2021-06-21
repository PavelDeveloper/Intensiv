package ru.androidschool.intensiv.data.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.TvShowsResponse
import ru.androidschool.intensiv.utils.on

object TvShowsRepositoryImpl : TvShowRepository {

    override val tvShows: Single<TvShowsResponse>
        get() = MovieApiClient.api.getTvPopular().on()
}
