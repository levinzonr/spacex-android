package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.interactors.*
import org.koin.dsl.module
import kotlin.math.sin

val interactorModule = module {
    factory { PostsInteractor(get()) }
    factory { GetUpcomingLaunchesInteractor(get()) }
    factory { GetPastLaunchesInteractor(get()) }
    factory { GetLaunchByIdInteractor(get()) }
    factory { GetAllRocketsInteractor(get()) }
    factory { GetRocketByIdInteractor(get()) }
}