package ru.androidschool.intensiv.data.details

import io.reactivex.Single
import ru.androidschool.intensiv.network.entity.MovieDetailResponse

interface DetailsRepository {
    fun getDetails(id: Int): Single<MovieDetailResponse>
}
