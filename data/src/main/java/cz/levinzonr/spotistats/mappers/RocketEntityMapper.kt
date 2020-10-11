package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.database.RocketEntity
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

object RocketEntityMapper : EntityMapper<RocketEntity, SpaceXRocket> {
    override fun toDomain(dto: RocketEntity): SpaceXRocket {
        return SpaceXRocket(
            id = dto.id,
            name = dto.name,
            images = dto.images,
            description = dto.description
        )
    }
}