package ru.androidschool.intensiv.data.details.mappers

import ru.androidschool.intensiv.data.details.dto.SpokenLanguageDto
import ru.androidschool.intensiv.data.details.vo.SpokenLanguage

object SpokenLanguageMapper {
    fun toValueObject(dto: SpokenLanguageDto): SpokenLanguage {
        return SpokenLanguage(
            iso = dto.iso,
            name = dto.name
        )
    }
}
