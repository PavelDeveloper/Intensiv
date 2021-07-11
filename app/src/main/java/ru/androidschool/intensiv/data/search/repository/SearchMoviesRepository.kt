package ru.androidschool.intensiv.data.search.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.movies.mappers.MovieResultMapper
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.SearchRepository
import ru.androidschool.intensiv.network.MovieApiClient

object SearchMoviesRepository : SearchRepository {
    override fun getSearchMovies(query: CharSequence): Single<MoviesResult> =
        MovieApiClient.apiClient.getSearchMovies(query).map { MovieResultMapper.toValueObject(it) }
}
