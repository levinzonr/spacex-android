package cz.levinzonr.spotistats.presentation.screens.main.rockets.detail

import androidx.navigation.fragment.navArgs
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.loadWithPlaceholder
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import org.koin.core.parameter.parametersOf

class RocketDetailsFragment : BaseFragment<State>() {

    private val args by navArgs<RocketDetailsFragmentArgs>()
    override val viewModel: RocketDetailsViewModel by viewModel { parametersOf(args.rocketId) }
    override val layoutRes: Int = R.layout.fragment_rocket_details


    override fun renderState(state: State) {
        state.rocket?.bind()
    }

    private fun SpaceXRocket.bind() {
        rocketDetailsImageIv.submitImages(images)
        rocketDetailsDescriptionTv.text = description
    }
}