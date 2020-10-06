package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.network.models.rocket.RocketResponse

object RocketResponseMapper : EntityMapper<RocketResponse, SpaceXRocket> {
    override fun toDomain(dto: RocketResponse): SpaceXRocket {
        return SpaceXRocket(
            id = dto.id,
            name = dto.name,
            images = dto.flickr_images.map { it },
            description = dto.description
        )
    }
}