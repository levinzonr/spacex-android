package cz.levinzonr.spotistats.entity

import cz.levinzonr.spotistats.cache.base.CachedEntity
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

data class SpaceXLaunchCachedEntity(
    val spaceXLaunch: SpaceXLaunch
) : CachedEntity() {
    override val id: String
        get() = spaceXLaunch.id
}