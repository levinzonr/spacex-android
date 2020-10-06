package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.repository.PrefManager
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository
import cz.levinzonr.spotistats.repository.SpaceXLaunchRepositoryImpl
import cz.levinzonr.spotistats.storage.PrefManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val repositoryModule = module {
    single<PrefManager> { PrefManagerImpl(androidContext()) }
    single<SpaceXLaunchRepository> { SpaceXLaunchRepositoryImpl(get())}
}