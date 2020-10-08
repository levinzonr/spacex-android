package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.entity.SpaceXRocketCachedEntity

class SpaceXRocketCachingStrategy(configuration: CachingConfiguration) :
    CachingStrategy<SpaceXRocketCachedEntity>(configuration) {

    override fun cacheIsValid(item: SpaceXRocketCachedEntity?): Boolean {
        return item != null
    }

    override fun cacheIsValidAndNotExpired(item: SpaceXRocketCachedEntity?): Boolean {
        return item != null && item.isValidNow(cacheValidityTime)
    }
}

class SpaceXRocketListCachingStrategy(
    configuration: CachingConfiguration
) : CachedItemListStrategy<SpaceXRocketCachedEntity>(configuration)