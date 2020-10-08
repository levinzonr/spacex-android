package cz.levinzonr.spotistats.cache.base



class CachingConfiguration(
    val remoteFallback: RemoteFallback,
    val cacheValidityTime: Long
) {

    enum class RemoteFallback {
        RETURN_CACHE, THROW_ERROR
    }
}