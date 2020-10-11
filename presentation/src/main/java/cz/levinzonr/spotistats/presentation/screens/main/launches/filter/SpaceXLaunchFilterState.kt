package cz.levinzonr.spotistats.presentation.screens.main.launches.filter

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.DateInterval
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import java.util.*

data class State(
    val selected: List<String> = listOf(),
    val rockets: List<SpaceXRocket> = listOf(),
    val applyFiltersEvent: SingleEvent<NewSpaceXFilterState?>? = null,
    val interval: DateInterval? = null
) : BaseState {
    val isFilterActive: Boolean get() = interval != null || selected.isNotEmpty()

    val currentFilter: SpaceXLaunchFilter?
        get() {
            return if (isFilterActive) SpaceXLaunchFilter(selected, interval) else null
        }
}

data class NewSpaceXFilterState(val filter: SpaceXLaunchFilter?)

sealed class Change : BaseChange {
    data class RocketsLoaded(val items: List<SpaceXRocket>) : Change()
    data class SelectedRocketsChanged(val items: List<String>) : Change()
    object LoadingStarted:  Change()
    object ResetFilters: Change()
    data class ConfirmCurrentFilter(val filter: SpaceXLaunchFilter) : Change()
}

sealed class Action : BaseAction {
    object Init : Action()
    data class RocketSelected(val rocket: SpaceXRocket) : Action()
    data class DateRangeChanged(val first: Calendar?, val last: Calendar?) : Action()
    object ApplyButtonClicked: Action()
    object ViewDisappeared: Action()
    object ClearFiltersButtonPressed: Action()
}