package cz.levinzonr.spotistats.presentation.screens.main.launches.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import coil.load
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchDetails
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import cz.levinzonr.spotistats.presentation.extensions.loadWithPlaceholder
import cz.levinzonr.spotistats.presentation.extensions.supportActionBar
import kotlinx.android.synthetic.main.fragment_space_x_launch_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SpaceXLaunchDetailFragment : BaseFragment<State>() {

    private val args: SpaceXLaunchDetailFragmentArgs by navArgs()
    override val viewModel: SpaceXLaunchDetailViewModel by viewModel { parametersOf(args.id) }
    override val layoutRes: Int = R.layout.fragment_space_x_launch_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderState(state: State) {
        state.launch?.bind()
        progressBar.isVisible = state.isLoading
    }

    private fun SpaceXLaunchDetails.bind() {
        supportActionBar?.title = launch.name
        launchDetailsDescriptionTv.text = launch.details
        imagesView.submitImages(launch.imagesUrls)
    }
}