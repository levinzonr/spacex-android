package cz.levinzonr.spotistats.presentation.screens.main.launches.filter

import cz.levinzonr.spotistats.domain.interactors.GetAllRocketsInteractor
import cz.levinzonr.spotistats.domain.interactors.asResult
import cz.levinzonr.spotistats.domain.interactors.invoke
import cz.levinzonr.spotistats.domain.models.DateInterval
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.util.*

class SpaceXLaunchFilterViewModel(
    private val getAllRocketsInteractor: GetAllRocketsInteractor
) : BaseViewModel<Action, Change, State>() {
    override val initialState: State = State()


    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when (change) {
            is Change.LoadingStarted -> state.copy()
            is Change.SelectedRocketsChanged -> state.copy(selected = change.items)
            is Change.RocketsLoaded -> state.copy(rockets = change.items)
            is Change.ConfirmCurrentFilter -> state.copy(
                selected = change.filter.rocketsIds,
                interval = change.filter.interval,
                applyFiltersEvent = SingleEvent(
                    NewSpaceXFilterState(change.filter)
                )
            )
            is Change.ResetFilters -> state.copy(
                selected = listOf(), interval = null, applyFiltersEvent = SingleEvent(
                    NewSpaceXFilterState(null)
                )
            )
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init)
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when (action) {
            is Action.Init -> bindInitAction(action)
            is Action.RocketSelected -> bindRocketSelectedAction(action)
            is Action.ApplyButtonClicked -> bindApplyFilterAction(action)
            is Action.ClearFiltersButtonPressed -> flow { emit(Change.ResetFilters) }
            is Action.ViewDisappeared -> flow { emit(Change.ResetFilters) }
            is Action.DateRangeChanged -> bindDateRangeChangedAction(action.first, action.last)
            is Action.ClearRocketsFilterAction -> bindClearRocketsAction()
            is Action.ClearDateFilterAction -> bindClearDateFilterAction()
        }
    }

    private fun bindInitAction(action: Action.Init): Flow<Change> = flowOnIO {
        getAllRocketsInteractor.asResult().invoke()
            .isError { Timber.e(it) }
            .isSuccess { emit(Change.RocketsLoaded(it)) }
    }


    private fun bindRocketSelectedAction(action: Action.RocketSelected): Flow<Change> = flow {
        val currentList = currentState.selected.toMutableList()
        if (currentList.contains(action.rocket.id)) {
            currentList.remove(action.rocket.id)
        } else {
            currentList.add(action.rocket.id)
        }
        emit(Change.SelectedRocketsChanged(currentList))
    }

    private fun bindApplyFilterAction(action: Action.ApplyButtonClicked): Flow<Change> = flow {
        val currentInterval = currentState.interval
        val rocketIds = currentState.selected
        emit(Change.ConfirmCurrentFilter(SpaceXLaunchFilter(rocketIds, currentInterval)))
    }

    private fun bindDateRangeChangedAction(start: Calendar?, end: Calendar?): Flow<Change> = flow {
        val dateRange = if (start != null && end != null) {
            DateInterval(
                startDate = start.time,
                endDate = end.time
            )
        } else null

        val currentFilter = currentState.currentFilter ?: SpaceXLaunchFilter()
        emit(Change.ConfirmCurrentFilter(currentFilter.copy(interval = dateRange)))
    }

    private fun bindClearDateFilterAction() : Flow<Change> = flow {
        val currentFilter = currentState.currentFilter ?: SpaceXLaunchFilter()
        emit(Change.ConfirmCurrentFilter(currentFilter.copy(interval = null)))
    }

    private fun bindClearRocketsAction() : Flow<Change> = flow {
        val currentFilter = currentState.currentFilter ?: SpaceXLaunchFilter()
        emit(Change.ConfirmCurrentFilter(currentFilter.copy(rocketsIds = listOf())))
    }

}