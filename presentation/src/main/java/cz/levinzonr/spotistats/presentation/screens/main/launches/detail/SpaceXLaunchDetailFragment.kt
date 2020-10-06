package cz.levinzonr.spotistats.presentation.screens.main.launches.detail

import androidx.core.view.isVisible
import coil.load
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_space_x_launch_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SpaceXLaunchDetailFragment : BaseFragment<State>() {

    override val viewModel: SpaceXLaunchDetailViewModel by viewModel { parametersOf("id") }
    override val layoutRes: Int = R.layout.fragment_space_x_launch_detail

    override fun renderState(state: State) {
        state.launch?.bind()
        progressBar.isVisible = state.isLoading
    }

    private fun SpaceXLaunch.bind() {
        launchDetailsDescriptionTv.text = details
        launchDetailsNameTv.text = name
        launchDetailsIv.load(imagesUrls.firstOrNull())
    }
}