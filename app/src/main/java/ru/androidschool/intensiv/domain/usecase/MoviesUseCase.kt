package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.utils.applySchedulers

class MoviesUseCase(private val repository: MovieRepository) {

    fun fetchMovies(): Observable<MoviesResult> {
        return repository.fetch().applySchedulers()
    }

    fun saveMovies(movies: List<Movie>): Completable {
        return repository.save(movies).applySchedulers()
    }

    fun deleteAllMovies(): Completable {
        return repository.deleteAll().applySchedulers()
    }
}
