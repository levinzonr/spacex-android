package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXRepository

class GetPastLaunchesInteractor(
    private val spaceXRepo: SpaceXRepository
) : NoInputInteractor<List<SpaceXLaunch>> {
    override suspend fun invoke(input: Unit): List<SpaceXLaunch> {
        return spaceXRepo.getPastLaunches()
    }
}