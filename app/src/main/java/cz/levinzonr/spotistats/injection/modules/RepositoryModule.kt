package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.repository.*
import cz.levinzonr.spotistats.repository.SpaceXCrewRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXLaunchRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXLaunchpadRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXRocketRepositoryImpl
import cz.levinzonr.spotistats.storage.PrefManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repositoryModule = module {
    single<PrefManager> { PrefManagerImpl(androidContext()) }
    single<SpaceXLaunchRepository> { SpaceXLaunchRepositoryImpl(get()) }
    single<SpaceXRocketsRepository> { SpaceXRocketRepositoryImpl(get()) }
    single<SpaceXLaunchpadRepository> { SpaceXLaunchpadRepositoryImpl(get()) }
    single<SpaceXCrewRepository> { SpaceXCrewRepositoryImpl(get()) }
}