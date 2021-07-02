package ru.androidschool.intensiv.data.movies

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<MovieDbEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movies: MovieDbEntity): Completable

    @Query("SELECT * FROM movies")
    fun getAll(): Observable<List<MovieDbEntity>>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    fun get(id: Long): Observable<MovieDbEntity>

    @Query("SELECT * FROM movies WHERE movieType = :type")
    fun get(type: MovieType): Observable<List<MovieDbEntity>>

    @Update
    fun update(movie: MovieDbEntity): Completable

    /**
     * у меня почему-то этот метод не добавляет новые элементы, если бд пустая и вообще
     * */
    @Update
    fun update(movies: List<MovieDbEntity>): Completable

    @Query("SELECT * FROM movies WHERE isLiked = :isLiked")
    fun getLiked(isLiked: Boolean = true): Observable<List<MovieDbEntity>>

    @Query("DELETE FROM movies WHERE movieType = :type AND isLiked = :isLiked")
    fun delete(type: MovieType, isLiked: Boolean = false): Completable

    @Query("DELETE FROM movies WHERE movieId = :id")
    fun delete(id: Long): Completable
}
