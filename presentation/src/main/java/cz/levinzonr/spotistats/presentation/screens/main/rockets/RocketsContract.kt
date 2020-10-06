package cz.levinzonr.spotistats.presentation.screens.main.rockets

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

data class State(
    val rockets: List<SpaceXRocket> = listOf(),
    val isLoading: Boolean = false
) : BaseState

sealed class Change : BaseChange {
    data class RocketsLoaded(val items: List<SpaceXRocket>) : Change()
    object LoadingStarted : Change()
}

sealed class Action : BaseAction {
    object Init: Action()
}