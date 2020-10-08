package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.SpaceXCrewMember
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.domain.repository.SpaceXCrewRepository
import cz.levinzonr.spotistats.mappers.CrewResponseMapper
import cz.levinzonr.spotistats.network.Api

class SpaceXCrewRepositoryImpl(
    private val api: Api
) : SpaceXCrewRepository {

    override suspend fun getCrewMemberById(id: String) : SpaceXCrewMember {
        return api.getCrewMemberById(id).let { CrewResponseMapper.toDomain(it) }
    }
}