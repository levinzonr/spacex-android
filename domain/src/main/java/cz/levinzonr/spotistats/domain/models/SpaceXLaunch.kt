package cz.levinzonr.spotistats.domain.models

import java.util.*

data class SpaceXLaunch(
    val id: String,
    val imagesUrls: List<String>,
    val date: Date,
    val name: String
)