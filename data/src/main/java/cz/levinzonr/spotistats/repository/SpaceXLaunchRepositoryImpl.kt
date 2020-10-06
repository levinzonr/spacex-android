package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository
import cz.levinzonr.spotistats.mappers.LaunchResponseMapper
import cz.levinzonr.spotistats.mappers.mapWithMapper
import cz.levinzonr.spotistats.network.Api

class SpaceXLaunchRepositoryImpl(
    private val api: Api
) : SpaceXLaunchRepository {
    override suspend fun getPastLaunches(): List<SpaceXLaunch> {
        return api.getPastLaunches().mapWithMapper(LaunchResponseMapper)
    }

    override suspend fun getUpcomingLaunches(): List<SpaceXLaunch> {
        return api.getUpcomingLaunches().mapWithMapper(LaunchResponseMapper)
    }

    override suspend fun getLaunchById(id: String): SpaceXLaunch {
        return api.getLaunchById(id).let { LaunchResponseMapper.toDomain(it) }
    }
}