package cz.levinzonr.spotistats.presentation.screens.main.launches

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter

data class State(
    val launches: List<SpaceXLaunch> = listOf(),
    val isLoading: Boolean = false,
    val isErrorView: Boolean = false
) : BaseState {

    val isEmptyView: Boolean
        get() = !isErrorView && !isLoading && launches.isEmpty()

}

enum class Mode {
    Upcoming, Past
}

sealed class Change : BaseChange {
    data class LaunchesLoaded(val items: List<SpaceXLaunch>, val filter: SpaceXLaunchFilter? = null) : Change()
    object LaunchesLoading : Change()
    object LaunchesLoadingError: Change()
}

sealed class Action : BaseAction {
    data class Init(val mode: Mode, val filter: SpaceXLaunchFilter?) : Action()
    data class OnFilterStateChanged(val filter: SpaceXLaunchFilter?) : Action()
}