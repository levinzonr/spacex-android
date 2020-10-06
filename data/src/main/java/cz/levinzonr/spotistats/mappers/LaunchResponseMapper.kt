package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.network.models.LaunchResponse
import java.util.*

object LaunchResponseMapper : EntityMapper<LaunchResponse, SpaceXLaunch> {
    override fun toDomain(dto: LaunchResponse): SpaceXLaunch {
        return SpaceXLaunch(
            id = dto.id,
            imagesUrls = dto.links.flickr.original.map { it },
            thumbnail = dto.resolveThumbnail(),
            date = Date(dto.date_unix.toLong()),
            name = dto.name,
            details = dto.details
        )
    }

    private fun LaunchResponse.resolveThumbnail() : String? {
        return listOf(links.flickr.small, links.flickr.original).flatten().firstOrNull()
    }
}