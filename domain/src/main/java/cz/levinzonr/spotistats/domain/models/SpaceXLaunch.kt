package cz.levinzonr.spotistats.domain.models

import java.util.*

data class SpaceXLaunch(
    val id: String,
    val imagesUrls: List<String>,
    val thumbnail: String? = null,
    val date: Date,
    val name: String,
    val crewMembersIds: List<String>,
    val rocketId: String,
    val links: SpaceXLinks,
    val launchpadId: String,
    val details: String? = "SpaceX's 20th and final Crew Resupply Mission under the original NASA CRS contract, this mission brings essential supplies to the International Space Station using SpaceX's reusable Dragon spacecraft. It is the last scheduled flight of a Dragon 1 capsule. (CRS-21 and up under the new Commercial Resupply Services 2 contract will use Dragon 2.) The external payload for this mission is the Bartolomeo ISS external payload hosting platform. Falcon 9 and Dragon will launch from SLC-40, Cape Canaveral Air Force Station and the booster will land at LZ-1. The mission will be complete with return and recovery of the Dragon capsule and down cargo."
)