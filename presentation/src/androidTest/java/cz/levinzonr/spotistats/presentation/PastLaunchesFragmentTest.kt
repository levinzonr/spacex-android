package cz.levinzonr.spotistats.presentation

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter
import cz.levinzonr.spotistats.presentation.screens.main.launches.*
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.NewSpaceXFilterState
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.State as FilterState
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.SpaceXLaunchFilterViewModel
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import cz.levinzonr.spotistats.presentation.utils.*
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.Action as FilterAction
import cz.levinzonr.spotistats.presentation.utils.createInjectionRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module

@RunWith(AndroidJUnit4ClassRunner::class)
class PastLaunchesFragmentTest {


    private val stateLiveData: MutableLiveData<State> = MutableLiveData()
    private val filterStateLiveData: MutableLiveData<FilterState> = MutableLiveData()

    private val filterViewModel: SpaceXLaunchFilterViewModel = mockk(relaxUnitFun = true) {
        every { observableState } returns filterStateLiveData
        every { navigationLiveData } returns MutableLiveData()

    }
    private val viewModel: SpaceXLaunchesViewModel = mockk(relaxUnitFun = true) {
        every { observableState } returns stateLiveData
        every { navigationLiveData } returns MutableLiveData()
    }

    private lateinit var scenario: FragmentScenario<PastLaunchesFragment>

    @get:Rule
    val fragmentRule = createInjectionRule(
        module {
            single(createdAtStart = true, override = true) { filterViewModel }
            single(createdAtStart = true, override = true) { viewModel }
        })


    @Before
    fun init() {

        scenario = launchFragmentInContainer(
            themeResId = R.style.MyTheme_DayNight,
        ) {
            PastLaunchesFragment()
        }


    }

    @Test
    fun testDefaultState() {
        onViewWithId(R.id.emptyView).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.progressBar).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.launchesRv).checkViewAs<RecyclerView> {
            assert(it.adapter?.itemCount == 0)
        }
    }

    @Test
    fun testLoadingState() {
        stateLiveData.postValue(State(isLoading = true))
        onViewWithId(R.id.emptyView).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.progressBar).checkVisibility(ViewMatchers.Visibility.VISIBLE)
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.launchesRv).checkViewAs<RecyclerView> {
            assert(it.adapter?.itemCount == 0)
        }
    }


    @Test
    fun testLaunchesLoadedState() {
        stateLiveData.postValue(
            State(
                isLoading = false,
                launches = List(10) { mockk(relaxed = true) })
        )
        onViewWithId(R.id.emptyView).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.progressBar).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.launchesRv).checkViewAs<RecyclerView> {
            assert(it.adapter?.itemCount == 10)
        }
    }


    @Test
    fun testEmptyState() {
        stateLiveData.postValue(State(isLoading = false, launches = listOf()))
        onViewWithId(R.id.emptyView).checkVisibility(ViewMatchers.Visibility.VISIBLE)
        onViewWithId(R.id.progressBar).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.GONE)
        onViewWithId(R.id.launchesRv).checkViewAs<RecyclerView> {
            assert(it.adapter?.itemCount == 0)
        }
    }

    @Test
    fun testFilterStateChanged() {
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.GONE)

        // Filter is active when rockets filteer is active
        filterStateLiveData.postValue(FilterState(selected = listOf("1")))
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.VISIBLE)

        // Filter is active when date filteer is active
        filterStateLiveData.postValue(FilterState(interval = mockk()))
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.VISIBLE)


        // Filter container gets hidden when reset happens
        filterStateLiveData.postValue(FilterState())
        onViewWithId(R.id.filterContainer).checkVisibility(ViewMatchers.Visibility.GONE)

        // ViewModel reacts when filter change event is fired
        val filter = mockk<SpaceXLaunchFilter>(relaxed = true)
        filterStateLiveData.postValue(
            FilterState(
                applyFiltersEvent = SingleEvent(
                    NewSpaceXFilterState(filter)
                )
            )
        )
        verify(exactly = 1) {
            viewModel.dispatch(Action.OnFilterStateChanged(filter))
        }

    }

    @Test
    fun testClearFilterAction() {
        // Clear button is hidden  by default
        verifyExceptionThrown {
            onViewWithId(R.id.launchesClearFiltersBtn).performClick()
        }

        // Make filter button visible
        filterStateLiveData.postValue(FilterState(selected = listOf("1")))
        onViewWithId(R.id.launchesClearFiltersBtn).checkVisibility(ViewMatchers.Visibility.VISIBLE)
        onViewWithId(R.id.launchesClearFiltersBtn).performClick()
        verify(exactly = 1) { filterViewModel.dispatch(FilterAction.ClearFiltersButtonPressed)}
    }

    @Test
    fun testLifecyleEvents() {
        scenario.moveToState(Lifecycle.State.STARTED)
        verify(exactly = 1) { viewModel.dispatch(Action.Init(Mode.Past)) }

        scenario.moveToState(Lifecycle.State.DESTROYED)
        verify(exactly = 1) { filterViewModel.dispatch(FilterAction.ViewDisappeared) }
    }


}