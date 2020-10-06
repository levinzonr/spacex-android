package cz.levinzonr.spotistats.presentation.screens.main.launches

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_launches.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.screens.main.launches.detail.SpaceXLaunchDetailFragmentDirections

abstract class SpaceXLaunchesFragment : BaseFragment<State>() {

    abstract val mode: Mode

    override val viewModel: SpaceXLaunchesViewModel by viewModel { parametersOf(mode) }

    override val layoutRes: Int = R.layout.fragment_launches

    private val adapter by lazy { SpaceXLaunchesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchesRv.init()
    }

    override fun renderState(state: State) {
        adapter.submitList(state.launches)
        progressBar.isVisible = state.isLoading
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
}

class PastLaunchesFragment: SpaceXLaunchesFragment() {
    override val mode: Mode = Mode.Past
}