package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.domain.repository.SpaceXRocketsRepository

class GetAllRocketsInteractor(
    private val repository: SpaceXRocketsRepository
) : NoInputInteractor<List<SpaceXRocket>> {
    override suspend fun invoke(input: Unit): List<SpaceXRocket> {
        return repository.getAllRockets()
    }
}