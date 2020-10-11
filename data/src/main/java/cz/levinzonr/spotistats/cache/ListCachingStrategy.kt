package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.cache.base.CachedEntity
import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import cz.levinzonr.spotistats.cache.base.CachingStrategy

open class ListCachingStrategy<T : CachedEntity>(
        configuration: CachingConfiguration
) : CachingStrategy<List<T>>(configuration) {

    override fun cacheIsValid(item: List<T>?): Boolean {
        return !item.isNullOrEmpty()
    }

    override fun cacheIsValidAndNotExpired(item: List<T>?): Boolean {
        return !item.isNullOrEmpty() && item.any { it.isValidNow(cacheValidityTime) }
    }
}
open class ItemCachingStrategy<T: CachedEntity>(
    configuration: CachingConfiguration
) : CachingStrategy<T>(configuration) {

    override fun cacheIsValid(item: T?): Boolean {
        return item != null
    }

    override fun cacheIsValidAndNotExpired(item: T?): Boolean {
        return item != null && item.isValidNow(cacheValidityTime)
    }
}
