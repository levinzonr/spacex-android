package cz.levinzonr.spotistats.presentation.injection

import cz.levinzonr.spotistats.presentation.screens.main.launches.Mode
import cz.levinzonr.spotistats.presentation.screens.main.launches.SpaceXLaunchesViewModel
import cz.levinzonr.spotistats.presentation.screens.onboarding.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel { SplashViewModel() }
    viewModel { (mode: Mode) -> SpaceXLaunchesViewModel(mode, get(), get()) }
}