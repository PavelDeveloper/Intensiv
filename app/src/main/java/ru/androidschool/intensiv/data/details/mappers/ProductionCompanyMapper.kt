package ru.androidschool.intensiv.data.details.mappers

import ru.androidschool.intensiv.data.details.dto.ProductionCompanyDto
import ru.androidschool.intensiv.data.details.vo.ProductionCompany

object ProductionCompanyMapper {
    fun toValueObject(dto: ProductionCompanyDto): ProductionCompany =
        ProductionCompany(
            id = dto.id,
            name = dto.name,
            logoPath = dto.logoPath,
            originCountry = dto.originCountry
        )
}
