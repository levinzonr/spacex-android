package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.cache.ItemCachingStrategy
import cz.levinzonr.spotistats.cache.ListCachingStrategy
import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.database.entity.LaunchEntity
import cz.levinzonr.spotistats.database.entity.RocketEntity
import cz.levinzonr.spotistats.domain.repository.*
import cz.levinzonr.spotistats.repository.SpaceXCrewRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXLaunchRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXLaunchpadRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXRocketRepositoryImpl
import cz.levinzonr.spotistats.storage.PrefManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val STRATEGY_ROCKETS = "rockets_strategy"
private const val STRATEGY_ROCKETS_ITEM = "rockets_strategy_item"
private const val STRATEGY_LAUNCHES = "launches_strategy_list"
private const val STRATEGY_LAUNCHES_ITEM = "launches_strategy_item"

val repositoryModule = module {

    single<PrefManager> { PrefManagerImpl(androidContext()) }

    single<SpaceXLaunchRepository> {
        SpaceXLaunchRepositoryImpl(
            get(), get(), get(
                named(
                    STRATEGY_LAUNCHES_ITEM
                )
            ), get(named(STRATEGY_LAUNCHES))
        )
    }


    single<SpaceXRocketsRepository> {
        SpaceXRocketRepositoryImpl(
            get(), get(), get(named(STRATEGY_ROCKETS_ITEM)), get(
                named(STRATEGY_ROCKETS)
            )
        )
    }
    single<SpaceXLaunchpadRepository> { SpaceXLaunchpadRepositoryImpl(get()) }
    single<SpaceXCrewRepository> { SpaceXCrewRepositoryImpl(get()) }


    factory<CachingStrategy<List<LaunchEntity>>>(named(STRATEGY_LAUNCHES)) {
        ListCachingStrategy(get())
    }

    factory<CachingStrategy<LaunchEntity>>(named(STRATEGY_LAUNCHES_ITEM)) {
        ItemCachingStrategy(get())
    }

    factory<CachingStrategy<List<RocketEntity>>>(named(STRATEGY_ROCKETS)) {
        ListCachingStrategy(get())
    }

    factory<CachingStrategy<RocketEntity>>(named(STRATEGY_ROCKETS_ITEM)) {
        ItemCachingStrategy(get())
    }


}