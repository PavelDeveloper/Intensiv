package ru.androidschool.intensiv.data.details.mappers

import ru.androidschool.intensiv.data.details.dto.MovieDetailInfoResponseDto
import ru.androidschool.intensiv.data.details.vo.MovieDetailInfo

object MovieDetailsMapper {
    fun toValueObject(dtoDetail: MovieDetailInfoResponseDto): MovieDetailInfo =
        MovieDetailInfo(
            adult = dtoDetail.adult,
            backdropPath = dtoDetail.backdropPath,
            belongsCollection = dtoDetail.belongsCollection,
            budget = dtoDetail.budget,
            genres = dtoDetail.genres.map { GenreMapper.toValueObject(it) },
            homepage = dtoDetail.homepage,
            id = dtoDetail.id,
            imdbId = dtoDetail.imdbId,
            originalLanguage = dtoDetail.originalLanguage,
            originalTitle = dtoDetail.originalTitle,
            overview = dtoDetail.overview,
            popularity = dtoDetail.popularity,
            posterPath = dtoDetail.posterPath,
            productionCompanies = dtoDetail.productionCompanies.map { ProductionCompanyMapper.toValueObject(it) },
            productionCountries = dtoDetail.productionCountries.map { ProductionCountryMapper.toValueObject(it) },
            releaseDate = dtoDetail.releaseDate,
            revenue = dtoDetail.revenue,
            runtime = dtoDetail.runtime,
            spokenLanguages = dtoDetail.spokenLanguages.map { SpokenLanguageMapper.toValueObject(it) },
            status = dtoDetail.status,
            tagline = dtoDetail.tagline,
            title = dtoDetail.title,
            video = dtoDetail.video,
            voteAverage = dtoDetail.voteAverage,
            voteCount = dtoDetail.voteCount
        )
}
