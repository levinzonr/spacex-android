package cz.levinzonr.spotistats.cache.base

abstract class CachedEntity {
    abstract val id: String
    var timestamp: Long = System.currentTimeMillis()


    fun isValidNow(maxCacheLife: Long): Boolean {
        return System.currentTimeMillis() - timestamp <= maxCacheLife
    }
}