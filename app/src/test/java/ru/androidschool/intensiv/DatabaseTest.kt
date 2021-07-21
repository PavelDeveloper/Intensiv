package ru.androidschool.intensiv

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.data.movies.mappers.MovieDbMapper
import ru.androidschool.intensiv.data.movies.vo.Movie

/**
 * сделал тест бд, вот только есть у меня очень сильное подозрение, что ничего в бд не добавляется
 * и я хз почему. проверяю кол-во сохранившихся databaseRule.database.movieDao().getAll() -> 0
 * */

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var movie: Movie
    private lateinit var movieDb: MovieDbEntity
    private lateinit var movieDbMapper: MovieDbMapper

    @get:Rule
    val databaseRule = DatabaseRule()

    @Before
    fun setup() {
        movie = Movie(
            title = "Suicide Squad",
            voteAverage = 5.91,
            posterPath = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
            popularity = 48.261451,
            overview = "The most dangerous former operative of the CIA",
            backdropPath = "/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg",
            originalLanguage = "en",
            id = 123,
            originalTitle = "Suicide Squad",
            isLiked = false,
            movieType = MovieType.DEFAULT,
            releaseDate = "2016-07-27"
        )
        movieDbMapper = MovieDbMapper
        movieDb = movieDbMapper.toDbObject(movie)
    }

    @Test
    fun insertMovie() {
        databaseRule.database.movieDao().insert(movieDb)
        databaseRule.database.movieDao().getAll()
            .subscribe { list ->
                assertEquals(list.size, 1)
            }
    }

    @Test
    fun updateMovie() {
        databaseRule.database.movieDao().insert(movieDbMapper.toDbObject(movie))
        val newTitle = "Jason Bourne"
        val newTitleMovie = movie.copy(title = newTitle)
        databaseRule.database.movieDao().update(movieDbMapper.toDbObject(newTitleMovie))
        databaseRule.database.movieDao().getAll()
            .subscribe {
                assertEquals(
                    newTitle,
                    it.map { movie -> movieDbMapper.toViewObject(movie) }.first().title
                )
            }
    }

    @Test
    fun deleteMovie() {
        databaseRule.database.movieDao().insert(movieDbMapper.toDbObject(movie))
        databaseRule.database.movieDao().delete(123)
        databaseRule.database.movieDao().getAll()
            .subscribe { list ->
                assertEquals(list.size, 0)
            }
    }
}
