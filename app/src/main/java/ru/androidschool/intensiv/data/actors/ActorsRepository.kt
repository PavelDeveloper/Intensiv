package ru.androidschool.intensiv.data.actors

import io.reactivex.Single
import ru.androidschool.intensiv.network.entity.CreditsResponse

interface ActorsRepository {
    fun getActors(id: Long): Single<CreditsResponse>
}
