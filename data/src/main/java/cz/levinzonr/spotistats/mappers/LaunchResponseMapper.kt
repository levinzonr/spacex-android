package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.domain.models.SpaceXLinks
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.network.models.LaunchResponse
import java.util.*

object LaunchResponseMapper : EntityMapper<LaunchResponse, SpaceXLaunch> {
    override fun toDomain(dto: LaunchResponse): SpaceXLaunch {
        return SpaceXLaunch(
            id = dto.id,
            imagesUrls = dto.links.flickr.original.map { it },
            thumbnail = dto.resolveThumbnail(),
            date = Date(dto.date_unix.toLong() * 1000),
            name = dto.name,
            details = dto.details,
            launchpadId = dto.launchpad,
            crewMembersIds = dto.crew,
            rocketId = dto.rocket,
            links = LinksResponseMapper.toDomain(dto.links)
        )
    }

    private fun LaunchResponse.resolveThumbnail() : String? {
        return listOf(links.flickr.small, links.flickr.original).flatten().firstOrNull()
    }
}