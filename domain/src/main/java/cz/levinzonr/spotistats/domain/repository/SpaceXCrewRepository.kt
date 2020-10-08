package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.SpaceXCrewMember

interface SpaceXCrewRepository {
    suspend fun getCrewMemberById(id: String) : SpaceXCrewMember
}