package ru.androidschool.intensiv.data.details.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.details.mappers.MovieDetailsMapper
import ru.androidschool.intensiv.data.details.vo.MovieDetailInfo
import ru.androidschool.intensiv.domain.repository.DetailsRepository
import ru.androidschool.intensiv.network.MovieApiClient

object DetailsMovieRepository : DetailsRepository {
    override fun getDetails(id: Long): Single<MovieDetailInfo> =
        MovieApiClient.api.getMovieDetail(movieId = id).map { MovieDetailsMapper.toValueObject(it) }
}
