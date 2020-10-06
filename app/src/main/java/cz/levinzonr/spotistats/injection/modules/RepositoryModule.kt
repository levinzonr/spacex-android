package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.repository.PrefManager
import cz.levinzonr.spotistats.domain.repository.SpaceXRepository
import cz.levinzonr.spotistats.repository.SpaceXMockRepository
import cz.levinzonr.spotistats.repository.SpaceXRepositoryImpl
import cz.levinzonr.spotistats.storage.PrefManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val repositoryModule = module {
    single<PrefManager> { PrefManagerImpl(androidContext()) }
    single<SpaceXRepository> { SpaceXRepositoryImpl(get())}
}