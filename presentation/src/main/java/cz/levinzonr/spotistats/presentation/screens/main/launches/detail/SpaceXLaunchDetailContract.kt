package cz.levinzonr.spotistats.presentation.screens.main.launches.detail

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchDetails


data class State(
    val launch: SpaceXLaunchDetails? = null,
    val isLoading: Boolean = false
) : BaseState

sealed class Change : BaseChange {
    object LoadingStarted: Change()
    data class LaunchLoaded(val launch: SpaceXLaunchDetails) : Change()
}

sealed class Action: BaseAction {
    data class Init(val id: String) : Action()
}

