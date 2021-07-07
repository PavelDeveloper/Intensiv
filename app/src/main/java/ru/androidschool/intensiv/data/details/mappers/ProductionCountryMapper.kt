package ru.androidschool.intensiv.data.details.mappers

import ru.androidschool.intensiv.data.details.dto.ProductionCountryDto
import ru.androidschool.intensiv.data.details.vo.ProductionCountry

object ProductionCountryMapper {
    fun toValueObject(dto: ProductionCountryDto): ProductionCountry =
        ProductionCountry(
            iso = dto.iso,
            name = dto.name
        )
}
