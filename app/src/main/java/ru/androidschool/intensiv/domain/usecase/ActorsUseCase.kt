package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.actors.vo.Credits
import ru.androidschool.intensiv.domain.repository.ActorsRepository
import ru.androidschool.intensiv.utils.applySchedulers

class ActorsUseCase(private val repository: ActorsRepository) {
    fun getActors(id: Long): Single<Credits> =
        repository.getActors(id).applySchedulers()
}
