package ru.androidschool.intensiv.data.actors.mappers

import ru.androidschool.intensiv.data.actors.dto.CreditsResponseDto
import ru.androidschool.intensiv.data.actors.vo.Credits

object CreditsMapper {
    fun toValueObject(dto: CreditsResponseDto): Credits {
        return Credits(
            id = dto.id,
            cast = dto.cast.map { ActorMapper.toValueObject(it) }
        )
    }
}
