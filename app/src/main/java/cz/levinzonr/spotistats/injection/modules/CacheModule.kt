package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import org.koin.dsl.module



val cacheModule = module {


    factory {
        CachingConfiguration(
            remoteFallback = CachingConfiguration.RemoteFallback.RETURN_CACHE,
            cacheValidityTime = 60_000
        )
    }




}