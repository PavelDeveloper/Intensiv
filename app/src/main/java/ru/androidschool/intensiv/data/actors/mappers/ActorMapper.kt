package ru.androidschool.intensiv.data.actors.mappers

import ru.androidschool.intensiv.data.actors.dto.ActorDto
import ru.androidschool.intensiv.data.actors.vo.Actor

object ActorMapper {
    fun toValueObject(dto: ActorDto): Actor =
        Actor(
            adult = dto.adult,
            gender = dto.gender,
            id = dto.id,
            department = dto.department,
            name = dto.name,
            originalName = dto.originalName,
            popularity = dto.popularity,
            profilePath = dto.profilePath,
            castId = dto.cast_id,
            character = dto.character,
            creditId = dto.creditId,
            order = dto.order
        )
}
