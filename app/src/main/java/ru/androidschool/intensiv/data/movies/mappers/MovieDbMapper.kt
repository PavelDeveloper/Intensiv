package ru.androidschool.intensiv.data.movies.mappers

import ru.androidschool.intensiv.data.base.ViewObjectMapper
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.vo.Movie

object MovieDbMapper : ViewObjectMapper<MovieDbEntity, Movie> {
    fun toDbObject(vo: Movie): MovieDbEntity =
        MovieDbEntity(
            posterPath = vo.posterPath,
            overview = vo.overview,
            releaseDate = vo.releaseDate,
            movieId = vo.id,
            originalTitle = vo.originalTitle,
            originalLanguage = vo.originalLanguage,
            backdropPath = vo.backdropPath,
            popularity = vo.popularity,
            voteAverage = vo.voteAverage,
            title = vo.title,
            movieType = vo.movieType,
            isLiked = vo.isLiked
        )

    override fun toViewObject(dto: MovieDbEntity): Movie =
        Movie(
            posterPath = dto.posterPath,
            overview = dto.overview,
            releaseDate = dto.releaseDate,
            id = dto.movieId,
            originalTitle = dto.originalTitle,
            originalLanguage = dto.originalLanguage,
            title = dto.title,
            backdropPath = dto.backdropPath,
            popularity = dto.popularity,
            voteAverage = dto.voteAverage,
            movieType = dto.movieType,
            isLiked = dto.isLiked
        )
}
