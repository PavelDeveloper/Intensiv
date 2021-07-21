package ru.androidschool.intensiv

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.movies.dto.MovieDto
import ru.androidschool.intensiv.data.movies.dto.MoviesResponseDto
import ru.androidschool.intensiv.data.movies.mappers.MovieResultMapper
import ru.androidschool.intensiv.data.movies.vo.Movie
import ru.androidschool.intensiv.data.movies.vo.MoviesResult
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class DtoUnitTest {

    private lateinit var movieResultMapper: MovieResultMapper

    private lateinit var moviesResponseDto: MoviesResponseDto

    private lateinit var moviesResult: MoviesResult

    @Before
    @Throws(Exception::class)
    fun setup() {
        movieResultMapper = MovieResultMapper

        moviesResponseDto = GsonBuilder()
            .setPrettyPrinting()
            .create()
            .fromJson(
                AssetsReader.read("popular_movies.json"),
                object : TypeToken<MoviesResponseDto>() {}.type
            )

        moviesResult = movieResultMapper.toValueObject(moviesResponseDto)
    }

    @Test
    fun checkMoviesMapping() {
        for (index in moviesResult.results.indices) {
            checkMovieItems(moviesResult.results[index], moviesResponseDto.results[index])
        }
    }

    private fun checkMovieItems(item: Movie, itemDto: MovieDto) {
        MatcherAssert.assertThat(item.title, CoreMatchers.`is`(itemDto.title))
        MatcherAssert.assertThat(item.backdropPath, CoreMatchers.`is`(itemDto.backdropPath))
        MatcherAssert.assertThat(item.originalLanguage, CoreMatchers.`is`(itemDto.originalLanguage))
        MatcherAssert.assertThat(item.originalTitle, CoreMatchers.`is`(itemDto.originalTitle))
        MatcherAssert.assertThat(item.overview, CoreMatchers.`is`(itemDto.overview))
        MatcherAssert.assertThat(item.popularity, CoreMatchers.`is`(itemDto.popularity))
        MatcherAssert.assertThat(item.posterPath, CoreMatchers.`is`(itemDto.posterPath))
        MatcherAssert.assertThat(item.releaseDate, CoreMatchers.`is`(itemDto.releaseDate))
        MatcherAssert.assertThat(item.voteAverage, CoreMatchers.`is`(itemDto.voteAverage))
    }
}

object AssetsReader {
    private const val ASSET_BASE_PATH = "../app/src/main/assets/"
    fun read(fileName: String): String {
        try {
            val br: BufferedReader =
                BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + fileName)))
            val sb: StringBuilder = StringBuilder()
            var line = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
            return sb.toString()
        } catch (e: IOException) {
            e.localizedMessage
        }
        return ""
    }
}
