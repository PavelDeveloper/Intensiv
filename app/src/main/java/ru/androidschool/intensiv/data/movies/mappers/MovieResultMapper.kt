package ru.androidschool.intensiv.data.movies.mappers

import ru.androidschool.intensiv.data.movies.dto.MoviesResponseDto
import ru.androidschool.intensiv.data.movies.vo.MoviesResult

object MovieResultMapper {
    fun toValueObject(dto: MoviesResponseDto): MoviesResult =
        MoviesResult(
            page = dto.page,
            results = dto.results.map { MovieMapper.toValueObject(it) }
        )
}
