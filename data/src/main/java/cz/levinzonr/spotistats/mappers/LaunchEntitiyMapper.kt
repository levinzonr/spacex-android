package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.database.LaunchEntity
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLinks

object LaunchEntityMapper : EntityMapper<LaunchEntity, SpaceXLaunch> {

    override fun toDomain(dto: LaunchEntity): SpaceXLaunch {
        return SpaceXLaunch(
            id = dto.id,
            imagesUrls = dto.imagesUrls,
            thumbnail = dto.thumbnail,
            date = dto.date,
            name = dto.name,
            crewMembersIds = dto.crewMembersIds,
            rocketId = dto.rocketId,
            links = SpaceXLinks(
                youtubeId = dto.youtubeId,
                wikiPage = dto.wikiPageUrl,
                articlePage = dto.articlePageUrl
            ),
            launchpadId = dto.launchpadId,
            details = dto.details
        )
    }
}