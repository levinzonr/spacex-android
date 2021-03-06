package cz.levinzonr.spotistats.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.presentation.extensions.observeNonNull
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.screens.main.MainActivity
import cz.levinzonr.spotistats.presentation.screens.onboarding.OnboardingActivity
import timber.log.Timber

abstract class BaseFragment<S: BaseState> : Fragment() {

    abstract val viewModel: BaseViewModel<*,*, S>

    abstract val layoutRes: Int

    abstract fun renderState(state: S)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.observableState.observeNonNull(viewLifecycleOwner) {
            Timber.d("${viewModel.javaClass.simpleName} state: $it")
            renderState(it)
        }

        viewModel.navigationLiveData.observeNonNull(viewLifecycleOwner) {
            Timber.d("Navigation Event: $it")
            it.consume()?.let(this::handleNavigationEvent)
        }
    }


    protected fun handleNavigationEvent(route: Route) {
        when(route) {
            is Route.Destination -> findNavController().navigate(route.navDirections)
            is Route.Main -> {
                activity?.finish()
                startActivity(MainActivity.createIntent(requireContext()))
            }
            is Route.Onboarding -> {
                activity?.finish()
                startActivity(Intent(context, OnboardingActivity::class.java))
            }
        }
    }


}
