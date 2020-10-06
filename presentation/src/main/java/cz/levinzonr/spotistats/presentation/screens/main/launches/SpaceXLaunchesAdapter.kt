package cz.levinzonr.spotistats.presentation.screens.main.launches

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_launch.view.*

class SpaceXLaunchesAdapter : ListAdapter<SpaceXLaunch, SpaceXLaunchesAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClicked: ((SpaceXLaunch) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_launch))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(spaceXLaunch: SpaceXLaunch) {
            view.launchIv.load(spaceXLaunch.thumbnail)
            view.launchNameTv.text = spaceXLaunch.name
            view.setOnClickListener { onItemClicked.invoke(spaceXLaunch) }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SpaceXLaunch>() {
            override fun areItemsTheSame(oldItem: SpaceXLaunch, newItem: SpaceXLaunch): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SpaceXLaunch, newItem: SpaceXLaunch): Boolean {
                return oldItem == newItem
            }
        }
    }

}