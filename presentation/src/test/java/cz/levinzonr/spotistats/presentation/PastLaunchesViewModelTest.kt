package cz.levinzonr.spotistats.presentation

import cz.levinzonr.spotistats.domain.interactors.*
import cz.levinzonr.spotistats.presentation.screens.main.launches.Action
import cz.levinzonr.spotistats.presentation.screens.main.launches.Mode
import cz.levinzonr.spotistats.presentation.screens.main.launches.SpaceXLaunchesViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter
import cz.levinzonr.spotistats.presentation.screens.main.launches.State
import cz.levinzonr.spotistats.presentation.utils.MainCoroutineRule
import cz.levinzonr.spotistats.presentation.utils.MockData
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PastLaunchesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val rule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUpcomingLaunchesInteractor = mockk<GetUpcomingLaunchesInteractor>()
    private val getPastLaunchesInteractor = mockk<GetPastLaunchesInteractor>()
    private val filterLaunchesInteractor = mockk<FilterLaunchesInteractor>()

    private val observer = mockk<Observer<State>>(relaxUnitFun = true)

    private lateinit var viewModel : SpaceXLaunchesViewModel

    private val allLaunches = MockData.launches

    @Before
    fun init() {
        coEvery { getUpcomingLaunchesInteractor.invoke() } throws Exception()
        coEvery { getPastLaunchesInteractor.invoke() } returns allLaunches
        viewModel =  SpaceXLaunchesViewModel(
            getPastLaunchesInteractor,
            getUpcomingLaunchesInteractor,
            filterLaunchesInteractor,
            rule.dispatcher
        )
        viewModel.observableState.observeForever(observer)
    }

    @Test
    fun `Given launches successfully loaded, when action Init is received, then State contains Launches`() {

        // GIVEN
        coEvery { getUpcomingLaunchesInteractor.invoke() } throws Exception()
        coEvery { getPastLaunchesInteractor.invoke() } returns allLaunches

        // WHEN
        viewModel.dispatch(Action.Init(Mode.Past, null))

        // THEN
        verifyOrder {
            observer.onChanged(State(isLoading = true))
            observer.onChanged(State(isLoading = false, launches = allLaunches))
        }
    }


    @Test
    fun `Given Launches failed to load, when action Init is received, then state contains empty lists and error flag`() {
        // GIVEN
        coEvery { getUpcomingLaunchesInteractor.invoke() } throws Exception()
        coEvery { getPastLaunchesInteractor.invoke() } throws Exception()

        // WHEN
        viewModel.dispatch(Action.Init(Mode.Past, null))


        // THEN
        verifyOrder {
            observer.onChanged(State(isLoading = true))
            observer.onChanged(State(isLoading = false, launches = listOf(), isErrorView = true))
        }
    }


    @Test
    fun `Given State contains Launches, when FilterStateChanged action is received with non-null filter, then State contains subset of launches and error`() {
        // GIVEN
        val filtered = allLaunches.subList(0, 10)
        coEvery { getPastLaunchesInteractor.invoke() } returns allLaunches
        coEvery { filterLaunchesInteractor.invoke(any()) } returns filtered

        // WHEN
        viewModel.dispatch(Action.Init(Mode.Past, null))
        viewModel.dispatch(Action.OnFilterStateChanged(SpaceXLaunchFilter(rocketsIds = listOf("1"))))


        // THEN
        verifyOrder {
            observer.onChanged(State(isLoading = true))
            observer.onChanged(State(isLoading = false, launches = allLaunches))
            observer.onChanged(State(launches = filtered))
        }

    }

    @Test
    fun `Given State contains Filtered Launches, when FilterStateChanged action is received with null filter, then State contains initial list`() {
        // GIVEN
        val filtered = allLaunches.subList(0, 10)
        val nonNullFilter = FilterLaunchesInteractor.Input(allLaunches, SpaceXLaunchFilter())
        coEvery { getPastLaunchesInteractor.invoke() } returns allLaunches
        coEvery { filterLaunchesInteractor.invoke(nonNullFilter) } returns filtered
        coEvery { filterLaunchesInteractor.invoke(FilterLaunchesInteractor.Input(allLaunches, null)) } returns allLaunches

        // WHEN
        viewModel.dispatch(Action.Init(Mode.Past, null))
        viewModel.dispatch(Action.OnFilterStateChanged(nonNullFilter.filter))
        viewModel.dispatch(Action.OnFilterStateChanged(null))


        // THEN
        verifyOrder {
            observer.onChanged(State(isLoading = true))
            observer.onChanged(State(isLoading = false, launches = allLaunches))
            observer.onChanged(State(launches = filtered))
            observer.onChanged(State(launches = allLaunches))
        }
    }

    @Test
    fun `Given Initial Filter is not Null, When View is Initlized, Then List Contains Filtered content`() {

        // GIVEN
        val filterd = allLaunches.subList(0, 10)
        val nonNullFilter = SpaceXLaunchFilter(listOf("1"))
        coEvery { getPastLaunchesInteractor.invoke() } returns allLaunches
        coEvery { filterLaunchesInteractor.invoke(any()) } returns filterd

        // WHEN
        viewModel.dispatch(Action.Init(Mode.Past, nonNullFilter))

        verifyOrder {
            observer.onChanged(State(isLoading = true))
            observer.onChanged(State(isLoading = false, launches = filterd))
        }

    }

}