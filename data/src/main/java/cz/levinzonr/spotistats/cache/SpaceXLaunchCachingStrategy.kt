package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.database.LaunchEntity
import cz.levinzonr.spotistats.entity.SpaceXLaunchCachedEntity

class SpaceXLaunchCachingStrategy(configuration: CachingConfiguration) :
    CachingStrategy<LaunchEntity>(configuration) {

    override fun cacheIsValid(item: LaunchEntity?): Boolean {
        return item != null
    }

    override fun cacheIsValidAndNotExpired(item: LaunchEntity?): Boolean {
        return item != null && item.isValidNow(cacheValidityTime)
    }
}

class SpaceXLaunchListCachingStrategy(
    configuration: CachingConfiguration
) : CachedItemListStrategy<LaunchEntity>(configuration)