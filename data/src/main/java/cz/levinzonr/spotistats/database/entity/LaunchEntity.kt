package cz.levinzonr.spotistats.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.levinzonr.spotistats.cache.base.CachedEntity
import cz.levinzonr.spotistats.domain.models.SpaceXLinks
import java.util.*

@Entity
data class LaunchEntity(
    @PrimaryKey
    override val id: String,
    val imagesUrls: List<String>,
    val thumbnail: String? = null,
    val date: Date,
    val name: String,
    val crewMembersIds: List<String>,
    val rocketId: String,
    val youtubeId: String?,
    val wikiPageUrl: String?,
    val articlePageUrl: String?,
    val launchpadId: String,
    val details: String?
) : CachedEntity()