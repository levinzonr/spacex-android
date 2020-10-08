package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.entity.SpaceXLaunchCachedEntity
import cz.levinzonr.spotistats.entity.SpaceXRocketCachedEntity
import org.koin.dsl.module

val cacheModule = module {

    factory {
        CachingConfiguration(
            remoteFallback = CachingConfiguration.RemoteFallback.RETURN_CACHE,
            cacheValidityTime = 100
        )
    }

    factory<CachingStrategy<SpaceXLaunchCachedEntity>> {
        SpaceXLaunchCachingStrategy(get())
    }

    factory<CachingStrategy<List<SpaceXLaunchCachedEntity>>> {
        SpaceXLaunchListCachingStrategy(get())
    }

    factory<CachingStrategy<SpaceXRocketCachedEntity>> {
        SpaceXRocketCachingStrategy(get())
    }

    factory<CachingStrategy<List<SpaceXRocketCachedEntity>>> {
        SpaceXRocketListCachingStrategy(get())
    }
}