package cz.levinzonr.spotistats.presentation.screens.main.launches

import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_launches.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.observeNonNull
import cz.levinzonr.spotistats.presentation.screens.main.launches.detail.SpaceXLaunchDetailFragmentDirections
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.SpaceXLaunchFilterViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import cz.levinzonr.spotistats.presentation.screens.main.launches.filter.Action as FilterAction

abstract class SpaceXLaunchesFragment : BaseFragment<State>() {

    abstract val mode: Mode
    abstract val isFilterAvailable: Boolean

    override val viewModel: SpaceXLaunchesViewModel by viewModel { parametersOf(mode) }

    private val  filterViewModel: SpaceXLaunchFilterViewModel by sharedViewModel()

    override val layoutRes: Int = R.layout.fragment_launches

    private val adapter by lazy { SpaceXLaunchesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(Action.Init(mode))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(isFilterAvailable)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchesRv.init()

        filterViewModel.observableState.observeNonNull(viewLifecycleOwner) { state ->
            filterContainer.isVisible = state.isFilterActive
            state.applyFiltersEvent?.consume()?.let {
                viewModel.dispatch(Action.OnFilterStateChanged(it.filter))
            }
        }

        launchesClearFiltersBtn.setOnClickListener {
            filterViewModel.dispatch(FilterAction.ClearFiltersButtonPressed)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        filterViewModel.dispatch(FilterAction.ViewDisappeared)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_launches, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.filterBtn -> {
                findNavController().navigate(R.id.action_global_spaceXLaunchFilterFragment)
                true
            }
            else -> false
        }
    }

    override fun renderState(state: State) {
        launchesRv.isGone = state.isLoading
        adapter.submitList(state.launches)
        progressBar.isVisible = state.isLoading
        emptyView.isVisible = state.launches.isEmpty() && !state.isLoading
    }


    private fun RecyclerView.init() {
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = this@SpaceXLaunchesFragment.adapter
        this@SpaceXLaunchesFragment.adapter.onItemClicked = {
            val route = SpaceXLaunchDetailFragmentDirections.actionGlobalSpaceXLaunchDetailFragment(it.id)
            findNavController().navigate(route)
        }
    }
}

class UpcomingLaunchesFragment : SpaceXLaunchesFragment() {
    override val mode: Mode = Mode.Upcoming
    override val isFilterAvailable: Boolean = false
}

class PastLaunchesFragment: SpaceXLaunchesFragment() {
    override val mode: Mode = Mode.Past
    override val isFilterAvailable: Boolean = true
}