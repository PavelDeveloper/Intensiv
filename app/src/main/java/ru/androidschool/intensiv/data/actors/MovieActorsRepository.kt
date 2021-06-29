package ru.androidschool.intensiv.data.actors

import io.reactivex.Single
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.utils.on

object MovieActorsRepository : ActorsRepository {
    override fun getActors(id: Long): Single<CreditsResponse> =
        MovieApiClient.api.getActors(movieId = id).on()
}
