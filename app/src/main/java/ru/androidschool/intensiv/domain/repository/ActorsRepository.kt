package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.actors.vo.Credits

interface ActorsRepository {
    fun getActors(id: Long): Single<Credits>
}
