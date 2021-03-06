package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository
import java.util.*

class SpaceXMockLaunchRepository : SpaceXLaunchRepository {

    private val images = listOf(
        "https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg",
        "https://live.staticflickr.com/65535/49636202657_e81210a3ca_o.jpg",
        "https://live.staticflickr.com/65535/49636202572_8831c5a917_o.jpg",
        "https://live.staticflickr.com/65535/49635401423_e0bef3e82f_o.jpg",
        "https://live.staticflickr.com/65535/49635985086_660be7062f_o.jpg"
    )

    override suspend fun getPastLaunches(): List<SpaceXLaunch> {
        return listOf()
    }

    override suspend fun getUpcomingLaunches(): List<SpaceXLaunch> {
       return listOf()
    }

    override suspend fun getLaunchById(id: String): SpaceXLaunch {
        throw Exception("")
    }
}