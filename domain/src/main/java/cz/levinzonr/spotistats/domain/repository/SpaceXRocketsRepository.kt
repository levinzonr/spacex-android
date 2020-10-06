package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.SpaceXRocket

interface SpaceXRocketsRepository {
    suspend fun getAllRockets() : List<SpaceXRocket>
    suspend fun getRocketById(id: String) : SpaceXRocket
}