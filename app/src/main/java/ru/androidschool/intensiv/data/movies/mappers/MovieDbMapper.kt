package ru.androidschool.intensiv.data.movies.mappers

import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.vo.Movie

object MovieDbMapper {
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

    fun toValueObject(dbObject: MovieDbEntity): Movie =
        Movie(
            posterPath = dbObject.posterPath,
            overview = dbObject.overview,
            releaseDate = dbObject.releaseDate,
            id = dbObject.movieId,
            originalTitle = dbObject.originalTitle,
            originalLanguage = dbObject.originalLanguage,
            title = dbObject.title,
            backdropPath = dbObject.backdropPath,
            popularity = dbObject.popularity,
            voteAverage = dbObject.voteAverage,
            movieType = dbObject.movieType,
            isLiked = dbObject.isLiked
        )
}
