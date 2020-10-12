package cz.levinzonr.spotistats.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.levinzonr.roxie.*
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseViewModel<A: BaseAction,C: BaseChange,S: BaseState>(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RoxieViewModel<A, S, C>() {

    private val _navigationLiveData: MutableLiveData<SingleEvent<Route>> = MutableLiveData()

    val navigationLiveData: LiveData<SingleEvent<Route>>
        get() = _navigationLiveData

    fun navigateTo(route: Route) {
        _navigationLiveData.postValue(SingleEvent(route))
    }


    fun<T> flowOnIO(block: suspend FlowCollector<T>.() -> Unit) : Flow<T> {
        return flow(block).flowOn(ioDispatcher)
    }

}
