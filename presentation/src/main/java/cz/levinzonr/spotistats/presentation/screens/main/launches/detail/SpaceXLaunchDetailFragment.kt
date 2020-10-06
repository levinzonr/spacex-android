package cz.levinzonr.spotistats.presentation.screens.main.launches.detail

import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SpaceXLaunchDetailFragment : BaseFragment<State>() {

    override val viewModel: SpaceXLaunchDetailViewModel by viewModel { parametersOf("id") }
    override val layoutRes: Int = R.layout.fragment_space_x_launch_detail

    override fun renderState(state: State) {

    }
}