package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXRepository

class GetUpcomingLaunchesInteractor(
    private val spaceXRepository: SpaceXRepository
) : NoInputInteractor<List<SpaceXLaunch>> {
    override suspend fun invoke(input: Unit): List<SpaceXLaunch> {
        return spaceXRepository.getUpcomingLaunches()
    }
}