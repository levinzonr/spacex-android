package cz.levinzonr.spotistats.mappers

import cz.levinzonr.spotistats.domain.models.SpaceXLinks
import cz.levinzonr.spotistats.network.models.Links

object LinksResponseMapper : EntityMapper<Links, SpaceXLinks> {
    override fun toDomain(dto: Links): SpaceXLinks {
        return SpaceXLinks(
            articlePage = dto.article,
            youtubeId = dto.youtube_id,
            wikiPage = dto.wikipedia
        )
    }
}