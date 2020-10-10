package cz.levinzonr.spotistats.presentation.screens.main.launches.filter

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_space_x_lanch_filter.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SpaceXLaunchFilterFragment : BaseFragment<State>() {

    override val viewModel: SpaceXLaunchFilterViewModel by sharedViewModel()

    override val layoutRes: Int = R.layout.fragment_space_x_lanch_filter

    private val adapter by lazy { SpaceXRocketFilterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        adapter.onClick = { viewModel.dispatch(Action.RocketSelected(it)) }
        launchDetailsApplyButton.setOnClickListener {
            viewModel.dispatch(Action.ApplyButtonClicked)
            findNavController().navigateUp()
        }
    }

    override fun renderState(state: State) {
        adapter.submitSelected(state.selected)
        adapter.submitList(state.rockets)
    }
}