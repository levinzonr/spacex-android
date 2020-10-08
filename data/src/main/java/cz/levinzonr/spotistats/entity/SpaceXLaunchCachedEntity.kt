package cz.levinzonr.spotistats.entity

import cz.levinzonr.spotistats.cache.base.CachedEntity
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

data class SpaceXLaunchCachedEntity(
    val spaceXRocket: SpaceXRocket
) : CachedEntity() {
    override val id: String
        get() = spaceXRocket.id
}