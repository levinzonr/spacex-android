package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.cache.ItemCachingStrategy
import cz.levinzonr.spotistats.cache.ListCachingStrategy
import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.database.LaunchEntity
import cz.levinzonr.spotistats.database.RocketEntity
import org.koin.core.qualifier.named
import org.koin.dsl.module



val cacheModule = module {


    factory {
        CachingConfiguration(
            remoteFallback = CachingConfiguration.RemoteFallback.RETURN_CACHE,
            cacheValidityTime = 60_000
        )
    }




}