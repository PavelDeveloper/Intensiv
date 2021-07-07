package ru.androidschool.intensiv.data.tvshows.mappers

import ru.androidschool.intensiv.data.tvshows.dto.TvShowsResponseDto
import ru.androidschool.intensiv.data.tvshows.vo.TvShowsResult

object TvShowsResultMapper {
    fun toValueObject(dto: TvShowsResponseDto): TvShowsResult =
        TvShowsResult(
            results = dto.results.map { TvShowMapper.toValueObject(it) },
            page = dto.page,
            totalPages = dto.totalPages,
            totalResults = dto.totalResults
        )
}
