package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch

interface SpaceXRepository {
    fun getPastLaunches() : List<SpaceXLaunch>
    fun getUpcomingLaunches() : List<SpaceXLaunch>
}