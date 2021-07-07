package ru.androidschool.intensiv.data.actors.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.actors.mappers.CreditsMapper
import ru.androidschool.intensiv.data.actors.vo.Credits
import ru.androidschool.intensiv.domain.repository.ActorsRepository
import ru.androidschool.intensiv.network.MovieApiClient

object MovieActorsRepository : ActorsRepository {
    override fun getActors(id: Long): Single<Credits> =
        MovieApiClient.api.getActors(movieId = id).map { CreditsMapper.toValueObject(it) }
}
