package cz.levinzonr.spotistats.presentation.screens.main.rockets

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.inflate
import cz.levinzonr.spotistats.presentation.extensions.loadWithPlaceholder
import kotlinx.android.synthetic.main.item_rocket.view.*

class RocketsAdapter : ListAdapter<SpaceXRocket, RocketsAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onClick: ((SpaceXRocket) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_rocket))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(spaceXRocket: SpaceXRocket) {
            view.rocketImageIv.loadWithPlaceholder(spaceXRocket.images.firstOrNull())
            view.rocketNameTv.text = spaceXRocket.name
            view.setOnClickListener { onClick.invoke(spaceXRocket) }
        }
    }

    companion object {
         val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SpaceXRocket>() {
            override fun areItemsTheSame(oldItem: SpaceXRocket, newItem: SpaceXRocket): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SpaceXRocket, newItem: SpaceXRocket): Boolean {
                return oldItem == newItem
            }
        }
    }
}