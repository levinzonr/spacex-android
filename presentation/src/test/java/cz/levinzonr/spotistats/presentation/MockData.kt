package cz.levinzonr.spotistats.presentation

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLinks
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import java.util.*

object MockData {

    private val names = arrayOf("SRT", "HELLO", "WORLD", "NORD", "SKYRIM")

    val rockets = List(10) {
        SpaceXRocket(
            id = it.toString(),
            name = names.random(),
            images = listOf(),
            description = "Rocket with id: $it"
        )
    }

    val launches = List(20) {
        SpaceXLaunch(
            id = it.toString(),
            imagesUrls = listOf(),
            thumbnail = null,
            date = Date(),
            name = names.random(),
            crewMembersIds = listOf(),
            rocketId = it.toString(),
            links = SpaceXLinks(null, null, null),
            launchpadId = it.toString(),
            details = ""
        )
    }

}