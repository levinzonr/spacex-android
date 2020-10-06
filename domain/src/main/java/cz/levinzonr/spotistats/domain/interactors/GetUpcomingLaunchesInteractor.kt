package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository

class GetUpcomingLaunchesInteractor(
    private val spaceXLaunchRepository: SpaceXLaunchRepository
) : NoInputInteractor<List<SpaceXLaunch>> {
    override suspend fun invoke(input: Unit): List<SpaceXLaunch> {
        return spaceXLaunchRepository.getUpcomingLaunches()
    }
}