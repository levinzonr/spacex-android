package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunchpad
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchpadRepository
import cz.levinzonr.spotistats.mappers.LaunchpadResponseMapper
import cz.levinzonr.spotistats.network.Api

class SpaceXLaunchpadRepositoryImpl(
    private val api: Api
) : SpaceXLaunchpadRepository {
    override suspend fun getLaunchPadById(id: String) : SpaceXLaunchpad {
        return api.getLaunchpadById(id).let { LaunchpadResponseMapper.toDomain(it) }
    }
}