package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.interactors.GetPastLaunchesInteractor
import cz.levinzonr.spotistats.domain.interactors.GetUpcomingLaunchesInteractor
import cz.levinzonr.spotistats.domain.interactors.PostsInteractor
import cz.levinzonr.spotistats.domain.repository.SpaceXRepository
import cz.levinzonr.spotistats.repository.SpaceXMockRepository
import org.koin.dsl.module
import kotlin.math.sin

val interactorModule = module {
    single { PostsInteractor(get()) }
    single { GetUpcomingLaunchesInteractor(get()) }
    single { GetPastLaunchesInteractor(get()) }
}