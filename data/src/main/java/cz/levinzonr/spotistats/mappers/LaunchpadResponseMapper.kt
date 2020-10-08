package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.domain.models.SpaceXLaunchpad
import cz.levinzonr.spotistats.network.models.launchpad.LaunchpadResponse

object LaunchpadResponseMapper : EntityMapper<LaunchpadResponse, SpaceXLaunchpad> {
    override fun toDomain(dto: LaunchpadResponse): SpaceXLaunchpad {
        return SpaceXLaunchpad(
            id = dto.id,
            name = dto.name,
            region = dto.region
        )
    }
}