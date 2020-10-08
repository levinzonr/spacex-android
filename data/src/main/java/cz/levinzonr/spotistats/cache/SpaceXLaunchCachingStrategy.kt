package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.entity.SpaceXLaunchCachedEntity
import cz.levinzonr.spotistats.entity.SpaceXRocketCachedEntity

class SpaceXLaunchCachingStrategy(configuration: CachingConfiguration) :
    CachingStrategy<SpaceXLaunchCachedEntity>(configuration) {

    override fun cacheIsValid(item: SpaceXLaunchCachedEntity?): Boolean {
        return item != null
    }

    override fun cacheIsValidAndNotExpired(item: SpaceXLaunchCachedEntity?): Boolean {
        return item != null && item.isValidNow(cacheValidityTime)
    }
}

class SpaceXLaunchListCachingStrategy(
    configuration: CachingConfiguration
) : CachedItemListStrategy<SpaceXLaunchCachedEntity>(configuration)