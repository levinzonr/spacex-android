package cz.levinzonr.spotistats.presentation.screens.main.launches

import cz.levinzonr.spotistats.domain.interactors.*
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import timber.log.Timber

class SpaceXLaunchesViewModel(
    private val getPastLaunchesInteractor: GetPastLaunchesInteractor,
    private val getUpcomingLaunchesInteractor: GetUpcomingLaunchesInteractor,
    private val filterLaunchesInteractor: FilterLaunchesInteractor,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<Action, Change, State>(dispatcher) {
    override val initialState: State = State()

    private var allLaunches: List<SpaceXLaunch> = listOf()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        Timber.d("Change: $change")
        when (change) {
            is Change.LaunchesLoaded -> state.copy(
                launches = change.items,
                isErrorView = false,
                isLoading = false
            )
            is Change.LaunchesLoading -> state.copy(isLoading = true, isErrorView = false)
            is Change.LaunchesLoadingError -> state.copy(isLoading = false, isErrorView = true)
        }
    }

    init {
        startActionsObserver()
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when (action) {
            is Action.Init -> flowOnIO { bindInitAction(action) }
            is Action.OnFilterStateChanged -> flowOnIO { bindFilterStateChangedAction(action.filter) }
        }
    }

    private suspend fun FlowCollector<Change>.bindInitAction(action: Action.Init) {
        emit(Change.LaunchesLoading)
        when (action.mode) {
            Mode.Upcoming -> getUpcomingLaunchesInteractor.asResult().invoke()
            Mode.Past -> getPastLaunchesInteractor.asResult().invoke()
        }.isError {
            Timber.e(it)
            emit(Change.LaunchesLoadingError)
        }.isSuccess {
            allLaunches = it
            if (action.filter != null)
                bindFilterStateChangedAction(action.filter)
            else
                emit(Change.LaunchesLoaded(it))

        }
    }

    private suspend fun FlowCollector<Change>.bindFilterStateChangedAction(filter: SpaceXLaunchFilter?) {
        val input = FilterLaunchesInteractor.Input(allLaunches, filter)
        val result = filterLaunchesInteractor.invoke(input)
        emit(Change.LaunchesLoaded(result, filter))
    }

}