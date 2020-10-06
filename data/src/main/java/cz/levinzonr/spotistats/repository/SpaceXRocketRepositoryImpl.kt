package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.domain.repository.SpaceXRocketsRepository
import cz.levinzonr.spotistats.mappers.RocketResponseMapper
import cz.levinzonr.spotistats.mappers.mapWithMapper
import cz.levinzonr.spotistats.network.Api

class SpaceXRocketRepositoryImpl(
    private val api: Api
) : SpaceXRocketsRepository {
    override suspend fun getAllRockets(): List<SpaceXRocket> {
        return api.getRockets().mapWithMapper(RocketResponseMapper)
    }

    override suspend fun getRocketById(id: String): SpaceXRocket {
        return api.getRocketById(id).let { RocketResponseMapper.toDomain(it) }
    }
}