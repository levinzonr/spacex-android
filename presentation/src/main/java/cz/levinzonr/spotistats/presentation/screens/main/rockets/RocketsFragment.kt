package cz.levinzonr.spotistats.presentation.screens.main.rockets

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import cz.levinzonr.spotistats.presentation.R
import kotlinx.android.synthetic.main.fragment_rockets.*

class RocketsFragment : BaseFragment<State>() {

    override val viewModel: RocketsViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_rockets

    private val adapter by lazy { RocketsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rocketsRv.init()
    }

    override fun renderState(state: State) {
        adapter.submitList(state.rockets)
        progressBar.isVisible = state.isLoading
    }

    private fun RecyclerView.init() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = this@RocketsFragment.adapter
    }
}