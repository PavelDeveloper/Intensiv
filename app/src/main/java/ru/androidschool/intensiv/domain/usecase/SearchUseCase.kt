package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.SearchRepository
import ru.androidschool.intensiv.utils.applySchedulers

class SearchUseCase(private val repository: SearchRepository) {

    fun getMovies(query: CharSequence): Single<MoviesResult> {
        return repository.getSearchMovies(query).applySchedulers()
    }
}
