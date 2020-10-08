package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.SpaceXLaunchpad

interface SpaceXLaunchpadRepository {
    suspend fun getLaunchPadById(id: String) : SpaceXLaunchpad
}