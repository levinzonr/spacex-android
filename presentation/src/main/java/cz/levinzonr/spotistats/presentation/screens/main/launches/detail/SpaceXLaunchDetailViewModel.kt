package cz.levinzonr.spotistats.presentation.screens.main.launches.detail

import androidx.lifecycle.ViewModel
import cz.levinzonr.spotistats.domain.interactors.GetLaunchByIdInteractor
import cz.levinzonr.spotistats.domain.interactors.asResult
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow

class SpaceXLaunchDetailViewModel(
    id: String,
    private val getLaunchByIdInteractor: GetLaunchByIdInteractor
) : BaseViewModel<Action, Change, State>() {
    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when(change) {
            is Change.LaunchLoaded -> state.copy(launch = change.launch, isLoading = false)
            is Change.LoadingStarted -> state.copy(isLoading = true)
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
        getLaunchByIdInteractor.asResult().invoke(action.id)
            .isSuccess { emit(Change.LaunchLoaded(it)) }
            .isError {  }
    }
}