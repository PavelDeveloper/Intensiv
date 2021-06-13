package ru.androidschool.intensiv.data

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.TvShowsResponse

object TvShowsRepository {

    val tvShows: Observable<TvShowsResponse>
        get() = MovieApiClient.api.getTvPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
