package ru.androidschool.intensiv.data.movies.mappers

import ru.androidschool.intensiv.data.movies.dto.MovieDto
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.data.movies.vo.Movie

object MovieMapper {
    fun toValueObject(dto: MovieDto): Movie =
        Movie(
            posterPath = dto.posterPath,
            overview = dto.overview,
            releaseDate = dto.releaseDate,
            id = dto.id,
            originalTitle = dto.originalTitle,
            originalLanguage = dto.originalLanguage,
            title = dto.title,
            backdropPath = dto.backdropPath,
            popularity = dto.popularity,
            voteAverage = dto.voteAverage,
            movieType = MovieType.DEFAULT,
            isLiked = false
        )
}
