package cz.levinzonr.spotistats.presentation.screens.main.launches.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import cz.levinzonr.spotistats.domain.models.SpaceXCrewMember
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.inflate
import cz.levinzonr.spotistats.presentation.extensions.loadWithPlaceholder
import kotlinx.android.synthetic.main.item_crew_member.view.*
import kotlinx.android.synthetic.main.item_launch.view.*

class CrewMembersAdapter : ListAdapter<SpaceXCrewMember, CrewMembersAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClicked: ((SpaceXLaunch) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_crew_member))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(member: SpaceXCrewMember) {
            view.crewMemberNameTv.text = member.name
            view.crewMemberImageIv.load(member.image) {
                transformations(CircleCropTransformation())
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SpaceXCrewMember>() {
            override fun areItemsTheSame(oldItem: SpaceXCrewMember, newItem: SpaceXCrewMember): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SpaceXCrewMember, newItem: SpaceXCrewMember): Boolean {
                return oldItem == newItem
            }
        }
    }

}