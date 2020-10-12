package cz.levinzonr.spotistats.presentation.screens.main.launches

import cz.levinzonr.spotistats.domain.interactors.*
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class SpaceXLaunchesViewModel(
    private val mode: Mode,
    private val getPastLaunchesInteractor: GetPastLaunchesInteractor,
    private val getUpcomingLaunchesInteractor: GetUpcomingLaunchesInteractor,
    private val filterLaunchesInteractor: FilterLaunchesInteractor,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<Action, Change, State>(dispatcher) {
    override val initialState: State = State()

    private var allLaunches: List<SpaceXLaunch> = listOf()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when (change) {
            is Change.LaunchesLoaded -> state.copy(launches = change.items, isLoading = false)
            is Change.LaunchesLoading -> state.copy(isLoading = true)
        }
    }

    init {
        startActionsObserver()
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when (action) {
            is Action.Init -> bindInitAction(action)
            is Action.OnFilterStateChanged -> bindFilterStateChangedAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init): Flow<Change> = flowOnIO {
        emit(Change.LaunchesLoading)
        when (action.mode) {
            Mode.Upcoming -> getUpcomingLaunchesInteractor.asResult().invoke()
            Mode.Past -> getPastLaunchesInteractor.asResult().invoke()
        }.isError {
            Timber.e(it)
            emit(Change.LaunchesLoaded(listOf()))
        }.isSuccess {
            allLaunches = it
            emit(Change.LaunchesLoaded(it))
        }
    }

    private fun bindFilterStateChangedAction(action: Action.OnFilterStateChanged): Flow<Change> =
        flowOnIO {
            val input = FilterLaunchesInteractor.Input(allLaunches, action.filter)
            val result = filterLaunchesInteractor.invoke(input)
            emit(Change.LaunchesLoaded(result))
        }

}