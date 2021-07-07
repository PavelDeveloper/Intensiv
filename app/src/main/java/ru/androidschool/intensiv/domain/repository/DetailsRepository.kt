package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.details.vo.MovieDetailInfo

interface DetailsRepository {
    fun getDetails(id: Long): Single<MovieDetailInfo>
}
