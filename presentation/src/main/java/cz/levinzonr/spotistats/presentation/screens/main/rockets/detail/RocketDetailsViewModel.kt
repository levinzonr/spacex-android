package cz.levinzonr.spotistats.presentation.screens.main.rockets.detail

import cz.levinzonr.spotistats.domain.interactors.GetRocketByIdInteractor
import cz.levinzonr.spotistats.domain.interactors.asResult
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class RocketDetailsViewModel(
    id: String,
    private val getRocketByIdInteractor: GetRocketByIdInteractor
) : BaseViewModel<Action, Change, State>() {

    override val initialState: State = State()
    override val reducer: suspend (state: State, change: Change) -> State = {state, change ->
        when(change) {
            is Change.LoadingStarted -> state.copy(isLoading = true)
            is Change.RocketLoaded -> state.copy(rocket = change.rocket, isLoading = false)
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init(id))
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when(action) {
            is Action.Init -> bindInitAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init) : Flow<Change> = flowOnIO {
        emit(Change.LoadingStarted)
        getRocketByIdInteractor.asResult().invoke(action.id)
            .isSuccess { emit(Change.RocketLoaded(it)) }
            .isError { Timber.e(it) }
    }
}