package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.interactors.GetLaunchByIdInteractor
import cz.levinzonr.spotistats.domain.interactors.GetPastLaunchesInteractor
import cz.levinzonr.spotistats.domain.interactors.GetUpcomingLaunchesInteractor
import cz.levinzonr.spotistats.domain.interactors.PostsInteractor
import org.koin.dsl.module

val interactorModule = module {
    single { PostsInteractor(get()) }
    single { GetUpcomingLaunchesInteractor(get()) }
    single { GetPastLaunchesInteractor(get()) }
    single { GetLaunchByIdInteractor(get()) }
}