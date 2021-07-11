package ru.androidschool.intensiv.data.actors.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.actors.mappers.CreditsMapper
import ru.androidschool.intensiv.data.actors.vo.Credits
import ru.androidschool.intensiv.domain.repository.ActorsRepository
import ru.androidschool.intensiv.network.MovieApiInterface

class MovieActorsRepository(private val apiClient: MovieApiInterface) : ActorsRepository {

    override fun getActors(id: Long): Single<Credits> =
        apiClient.getActors(movieId = id).map { CreditsMapper.toValueObject(it) }
}
