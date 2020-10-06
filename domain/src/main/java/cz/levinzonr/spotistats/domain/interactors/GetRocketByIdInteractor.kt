package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.domain.repository.SpaceXRocketsRepository

class GetRocketByIdInteractor(
    private val spaceXRocketsRepository: SpaceXRocketsRepository
) : Interactor<String, SpaceXRocket> {
    override suspend fun invoke(input: String): SpaceXRocket {
        return spaceXRocketsRepository.getRocketById(input)
    }
}