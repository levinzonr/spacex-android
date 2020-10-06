package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXRepository
import java.util.*

class SpaceXMockRepository : SpaceXRepository {

    private val images = listOf(
        "https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg",
        "https://live.staticflickr.com/65535/49636202657_e81210a3ca_o.jpg",
        "https://live.staticflickr.com/65535/49636202572_8831c5a917_o.jpg",
        "https://live.staticflickr.com/65535/49635401423_e0bef3e82f_o.jpg",
        "https://live.staticflickr.com/65535/49635985086_660be7062f_o.jpg"
    )

    override suspend fun getPastLaunches(): List<SpaceXLaunch> {
        return List(10) {
            SpaceXLaunch(
                id = UUID.randomUUID().toString(),
                imagesUrls = images,
                date =Date(),
                name = "CSR-X"
            )
        }
    }

    override suspend fun getUpcomingLaunches(): List<SpaceXLaunch> {
        return List(10) {
            SpaceXLaunch(
                id = UUID.randomUUID().toString(),
                imagesUrls = images,
                date =Date(),
                name = "CSR-X"
            )
        }
    }

    override suspend fun getLaunchById(id: String): SpaceXLaunch {
        return SpaceXLaunch(
            id = UUID.randomUUID().toString(),
            imagesUrls = images,
            date =Date(),
            name = "CSR-X"
        )
    }
}