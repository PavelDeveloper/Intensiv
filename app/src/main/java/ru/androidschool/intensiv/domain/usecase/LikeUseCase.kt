package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import ru.androidschool.intensiv.domain.repository.LikeRepository
import ru.androidschool.intensiv.utils.applySchedulers

class LikeUseCase(private val repository: LikeRepository) {

    fun fetchMovies(): Observable<MoviesResult> {
        return repository.fetch().applySchedulers()
    }

    fun delete(id: Long): Completable =
        repository.delete(id).applySchedulers()

    fun getLiked(id: Long): Observable<MovieDbEntity> =
        repository.getLiked(id).applySchedulers()

    fun update(movie: MovieDbEntity): Completable =
        repository.update(movie).applySchedulers()
}
