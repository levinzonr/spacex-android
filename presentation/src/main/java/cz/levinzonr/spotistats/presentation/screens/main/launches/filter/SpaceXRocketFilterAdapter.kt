package cz.levinzonr.spotistats.presentation.screens.main.launches.filter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_rocket_filter.view.*
import timber.log.Timber

class SpaceXRocketFilterAdapter : ListAdapter<SpaceXRocket, SpaceXRocketFilterAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    private var selected: List<String> = listOf()


    var onClick : ((SpaceXRocket) -> Unit) = {}

    fun submitSelected(list: List<String>) {
        this.selected = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_rocket_filter))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(rocket: SpaceXRocket) {
            val isSelected = selected.find { it == rocket.id } != null
            view.filterRockeCheckbox.text = rocket.name
            view.filterRockeCheckbox.isChecked = isSelected
            view.setOnClickListener { onClick.invoke(rocket) }
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