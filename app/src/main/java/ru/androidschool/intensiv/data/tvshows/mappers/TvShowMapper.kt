package ru.androidschool.intensiv.data.tvshows.mappers

import ru.androidschool.intensiv.data.tvshows.dto.TvShowDto
import ru.androidschool.intensiv.data.tvshows.vo.TvShow

object TvShowMapper {
    fun toValueObject(dto: TvShowDto): TvShow =
        TvShow(
            id = dto.id,
            name = dto.name,
            originCountry = dto.originCountry,
            originalLanguage = dto.originalLanguage,
            backdropPath = dto.backdropPath,
            firstAirDate = dto.firstAirDate,
            genreIds = dto.genreIds,
            originalName = dto.originalName,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            voteAverage = dto.voteAverage,
            voteCount = dto.voteCount
        )
}
