package cz.levinzonr.spotistats.presentation.injection

import cz.levinzonr.spotistats.presentation.screens.main.launches.Mode
import cz.levinzonr.spotistats.presentation.screens.main.launches.SpaceXLaunchesViewModel
import cz.levinzonr.spotistats.presentation.screens.main.launches.detail.SpaceXLaunchDetailViewModel
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.SpaceXLaunchFilterViewModel
import cz.levinzonr.spotistats.presentation.screens.main.rockets.RocketsViewModel
import cz.levinzonr.spotistats.presentation.screens.main.rockets.detail.RocketDetailsViewModel
import cz.levinzonr.spotistats.presentation.screens.onboarding.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { SplashViewModel() }
    viewModel { SpaceXLaunchesViewModel(get(), get(), get()) }
    viewModel { (id: String) -> SpaceXLaunchDetailViewModel(id, get()) }
    viewModel { (id: String) -> RocketDetailsViewModel(id, get()) }
    viewModel { RocketsViewModel(get()) }
    viewModel { SpaceXLaunchFilterViewModel(get()) }
}