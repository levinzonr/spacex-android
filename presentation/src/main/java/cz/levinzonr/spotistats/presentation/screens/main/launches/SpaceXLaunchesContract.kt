package cz.levinzonr.spotistats.presentation.screens.main.launches

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch

data class State(
    val launches: List<SpaceXLaunch> = listOf(),
    val isLoading: Boolean = false
) : BaseState

enum class Mode {
    Upcoming, Past
}

sealed class Change : BaseChange {
    data class LaunchesLoaded(val items: List<SpaceXLaunch>) : Change()
    object LaunchesLoading : Change()
}

sealed class Action : BaseAction {
    data class Init(val mode: Mode) : Action()
}