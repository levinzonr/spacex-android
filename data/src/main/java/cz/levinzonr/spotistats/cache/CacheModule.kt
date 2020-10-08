package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import org.koin.dsl.module

val cacheModule = module {

    factory {
        CachingConfiguration(
            remoteFallback = CachingConfiguration.RemoteFallback.RETURN_CACHE,
            cacheValidityTime = 100
        )
    }

    factory{
        SpaceXLaunchCachingStrategy(get())
    }

    factory {
        SpaceXLaunchListCachingStrategy(get())
    }

    factory {
        SpaceXRocketCachingStrategy(get())
    }

    factory {
        SpaceXRocketListCachingStrategy(get())
    }
}