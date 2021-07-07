package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.details.vo.MovieDetailInfo
import ru.androidschool.intensiv.domain.repository.DetailsRepository
import ru.androidschool.intensiv.utils.applySchedulers

class DetailsUseCase(private val repository: DetailsRepository) {
    fun getDetails(id: Long): Single<MovieDetailInfo> =
        repository.getDetails(id).applySchedulers()
}
