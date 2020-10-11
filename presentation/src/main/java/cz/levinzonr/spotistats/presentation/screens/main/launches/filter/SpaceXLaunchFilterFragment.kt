package cz.levinzonr.spotistats.presentation.screens.main.launches.filter

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.archit.calendardaterangepicker.customviews.CalendarListener
import cz.levinzonr.spotistats.domain.extensions.format
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_space_x_lanch_filter.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class SpaceXLaunchFilterFragment : BaseFragment<State>() {

    override val viewModel: SpaceXLaunchFilterViewModel by sharedViewModel()

    override val layoutRes: Int = R.layout.fragment_space_x_lanch_filter

    private val adapter by lazy { SpaceXRocketFilterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.init()
        setupCalenderView()
        setupListeners()
    }

    override fun renderState(state: State) {
        adapter.submitSelected(state.selected)
        adapter.submitList(state.rockets)
        val interval = state.interval
        filterDateClearBtn.isInvisible = interval == null
        filterRocketsClearBtn.isInvisible = state.selected.isEmpty()
        if (interval == null) calendarView.resetAllSelectedViews()
    }

    private fun setupCalenderView() {
        calendarView.setCalendarListener(object : CalendarListener {
            override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                viewModel.dispatch(Action.DateRangeChanged(startDate, endDate))
            }

            override fun onFirstDateSelected(startDate: Calendar) {
                // no-op
            }
        })
    }

    private fun setupListeners() {
        launchDetailsApplyButton.setOnClickListener {
            viewModel.dispatch(Action.ApplyButtonClicked)
            findNavController().navigateUp()
        }

        filterRocketsClearBtn.setOnClickListener {
            viewModel.dispatch(Action.ClearRocketsFilterAction)
        }
        filterDateClearBtn.setOnClickListener {
            viewModel.dispatch(Action.ClearDateFilterAction)
        }
    }


    private fun RecyclerView.init() {
        adapter = this@SpaceXLaunchFilterFragment.adapter
        this@SpaceXLaunchFilterFragment.adapter.onClick =
            { viewModel.dispatch(Action.RocketSelected(it)) }
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}