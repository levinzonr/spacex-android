package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository

class GetPastLaunchesInteractor(
    private val spaceXLaunchRepo: SpaceXLaunchRepository
) : NoInputInteractor<List<SpaceXLaunch>> {
    override suspend fun invoke(input: Unit): List<SpaceXLaunch> {
        return spaceXLaunchRepo.getPastLaunches()
    }
}