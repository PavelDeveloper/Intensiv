package ru.androidschool.intensiv.data.movies

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<MovieDbEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movies: MovieDbEntity): Completable

    @Query("SELECT * FROM movies")
    fun getAll(): Flowable<List<MovieDbEntity>>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    fun get(id: Long): Flowable<MovieDbEntity>

    @Query("SELECT * FROM movies WHERE movieType = :type")
    fun get(type: MovieType): Flowable<List<MovieDbEntity>>

    @Update
    fun update(movie: MovieDbEntity): Completable

    // а как вообще можно ли записать для дефолтного?
    @Query("SELECT * FROM movies WHERE isLiked = :isLiked")
    fun getLiked(isLiked: Boolean = true): Flowable<List<MovieDbEntity>>

    // а как вообще можно ли записать для дефолтного? что-то типа "DELETE FROM movies WHERE movieType = MovieType.PLAYING"
    @Query("DELETE FROM movies WHERE movieType = :type AND isLiked = :isLiked")
    fun delete(type: MovieType, isLiked: Boolean = false): Completable

    @Query("DELETE FROM movies WHERE movieId = :id")
    fun delete(id: Long): Completable

    // вот этот вариант что-то нормально не отрабатывает: удаление/запись должна быть в беграунде, но метод, помеченный этой аннотацией
    // не позволяет вернуть completable
    /* @Transaction
     open fun cleanUpdateAllData(movies: List<MovieDbEntity>, type: MovieType) {
         deleteAllType(type)
         insertAll(movies)
     }*/
}
