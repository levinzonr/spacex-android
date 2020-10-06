package cz.levinzonr.spotistats.presentation.screens.onboarding.splash

import android.os.Bundle
import android.view.View
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<State>() {

    override val viewModel: SplashViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(Action.Init)
    }

    override fun renderState(state: State) {

    }

}