package cz.levinzonr.spotistats.presentation.screens.main.rockets.detail

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

data class State(
    val rocket: SpaceXRocket? = null,
    val isLoading: Boolean = false
) : BaseState


sealed class Change : BaseChange {
    object LoadingStarted: Change()
    data class RocketLoaded(val rocket: SpaceXRocket): Change()
}

sealed class Action : BaseAction {
    data class Init(val id: String) : Action()
}