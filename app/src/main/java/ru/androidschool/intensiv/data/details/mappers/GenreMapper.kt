package ru.androidschool.intensiv.data.details.mappers

import ru.androidschool.intensiv.data.base.ViewObjectMapper
import ru.androidschool.intensiv.data.details.dto.GenreDto
import ru.androidschool.intensiv.data.details.vo.Genre

object GenreMapper : ViewObjectMapper<GenreDto, Genre> {
    override fun toViewObject(dto: GenreDto): Genre =
        Genre(
            id = dto.id,
            name = dto.name
        )
}
