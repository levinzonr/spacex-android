package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.repository.*
import cz.levinzonr.spotistats.repository.SpaceXCrewRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXLaunchRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXLaunchpadRepositoryImpl
import cz.levinzonr.spotistats.repository.SpaceXRocketRepositoryImpl
import cz.levinzonr.spotistats.storage.PrefManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DS_LAUNCH = "launch"
private const val DS_ROCKET = "rockets"

val repositoryModule = module {

    single<PrefManager> { PrefManagerImpl(androidContext()) }
    single<SpaceXLaunchRepository> { SpaceXLaunchRepositoryImpl(get(), get(), get(), get()) }
    single<SpaceXRocketsRepository> { SpaceXRocketRepositoryImpl(get()) }
    single<SpaceXLaunchpadRepository> { SpaceXLaunchpadRepositoryImpl(get()) }
    single<SpaceXCrewRepository> { SpaceXCrewRepositoryImpl(get()) }


}