package cz.levinzonr.spotistats.entity

import cz.levinzonr.spotistats.cache.base.CachedEntity
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

class SpaceXRocketCachedEntity(val spaceXRocket: SpaceXRocket) : CachedEntity() {
    override val id: String
        get() = spaceXRocket.id
}