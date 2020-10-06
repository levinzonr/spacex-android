package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository

class GetLaunchByIdInteractor(
    private val repository: SpaceXLaunchRepository
) : Interactor<String, SpaceXLaunch> {
    override suspend fun invoke(input: String): SpaceXLaunch {
        return repository.getLaunchById(input)
    }
}