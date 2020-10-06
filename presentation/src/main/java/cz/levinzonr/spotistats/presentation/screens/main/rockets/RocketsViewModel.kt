package cz.levinzonr.spotistats.presentation.screens.main.rockets

import cz.levinzonr.spotistats.domain.interactors.GetAllRocketsInteractor
import cz.levinzonr.spotistats.domain.interactors.asResult
import cz.levinzonr.spotistats.domain.interactors.invoke
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class RocketsViewModel(
    private val getAllRocketsInteractor: GetAllRocketsInteractor
) : BaseViewModel<Action, Change, State>() {

    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when(change) {
            is Change.LoadingStarted -> state.copy(isLoading = true)
            is Change.RocketsLoaded -> state.copy(rockets = change.items, isLoading = false)
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init)
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when(action) {
            is Action.Init -> bindInitAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init) = flowOnIO<Change> {
        emit(Change.LoadingStarted)
        getAllRocketsInteractor.asResult().invoke()
            .isError {
                Timber.e(it)
                emit(Change.RocketsLoaded(listOf()))
            }.isSuccess {
                emit(Change.RocketsLoaded(it))
            }
    }
}