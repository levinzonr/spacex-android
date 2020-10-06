package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch

interface SpaceXRepository {
    suspend fun getPastLaunches() : List<SpaceXLaunch>
    suspend fun getUpcomingLaunches() : List<SpaceXLaunch>
    suspend fun getLaunchById(id: String) : SpaceXLaunch
}