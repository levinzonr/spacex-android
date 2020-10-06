package cz.levinzonr.spotistats.presentation.screens.main.launches

import cz.levinzonr.spotistats.domain.interactors.GetPastLaunchesInteractor
import cz.levinzonr.spotistats.domain.interactors.GetUpcomingLaunchesInteractor
import cz.levinzonr.spotistats.domain.interactors.asResult
import cz.levinzonr.spotistats.domain.interactors.invoke
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow

class SpaceXLaunchesViewModel (
    private val mode: Mode,
    private val getPastLaunchesInteractor: GetPastLaunchesInteractor,
    private val getUpcomingLaunchesInteractor: GetUpcomingLaunchesInteractor,
) : BaseViewModel<Action, Change, State>(){
    override val initialState: State = State()


    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when(change) {
            is Change.LaunchesLoaded -> state.copy(launches = change.items, isLoading = false)
            is Change.LaunchesLoading -> state.copy(isLoading = true)
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init(mode))
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when(action) {
            is Action.Init -> bindInitAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init) : Flow<Change> = flowOnIO {
        when(action.mode) {
            Mode.Upcoming -> getUpcomingLaunchesInteractor.asResult().invoke()
            Mode.Past -> getPastLaunchesInteractor.asResult().invoke()
        }.isError {
            emit(Change.LaunchesLoaded(listOf()))
        }.isSuccess {
            emit(Change.LaunchesLoaded(it))
        }
    }
}