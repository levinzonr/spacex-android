package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXRepository
import cz.levinzonr.spotistats.mappers.LaunchResponseMapper
import cz.levinzonr.spotistats.mappers.mapWithMapper
import cz.levinzonr.spotistats.network.Api

class SpaceXRepositoryImpl(
    private val api: Api
) : SpaceXRepository {
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