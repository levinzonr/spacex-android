package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.domain.models.SpaceXCrewMember
import cz.levinzonr.spotistats.network.models.crew.CrewResponse

object CrewResponseMapper : EntityMapper<CrewResponse, SpaceXCrewMember> {
    override fun toDomain(dto: CrewResponse): SpaceXCrewMember {
        return SpaceXCrewMember(
            id = dto.id,
            name = dto.name ?: "",
            image = dto.image
        )
    }
}