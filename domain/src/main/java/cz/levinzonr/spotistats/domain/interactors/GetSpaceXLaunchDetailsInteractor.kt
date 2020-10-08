package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.SpaceXCrewMember
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchDetails
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchpad
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.domain.repository.SpaceXCrewRepository
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchpadRepository
import cz.levinzonr.spotistats.domain.repository.SpaceXRocketsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetSpaceXLaunchDetailsInteractor(
    private val spaceXCrewRepository: SpaceXCrewRepository,
    private val spaceXLaunchpadRepository: SpaceXLaunchpadRepository,
    private val spaceXRocketsRepository: SpaceXRocketsRepository,
    private val spaceXLaunchRepository: SpaceXLaunchRepository
) : Interactor<String, SpaceXLaunchDetails> {

    override suspend fun invoke(input: String): SpaceXLaunchDetails = coroutineScope {
        val launch = spaceXLaunchRepository.getLaunchById(input)
        val rocketDeferred = async { tryLoadingRocket(launch.rocketId) }
        val crewDeferred = launch.crewMembersIds.map { async { tryLoadingCrewMember(it) } }
        val launchpadDeferred = async { tryLoadingLaunchPad(launch.launchpadId) }
        SpaceXLaunchDetails(
            spaceXLaunchpad = launchpadDeferred.await(),
            launch = launch,
            crew = crewDeferred.awaitAll().filterNotNull(),
            rocket = rocketDeferred.await()
        )
    }

    private suspend fun tryLoadingRocket(id: String) : SpaceXRocket? {
        return try {
            spaceXRocketsRepository.getRocketById(id)
        } catch (t: Throwable) {
            null
        }
    }

    private suspend fun tryLoadingCrewMember(id: String) : SpaceXCrewMember? {
        return try {
            spaceXCrewRepository.getCrewMemberById(id)
        } catch (t: Throwable) {
            null
        }
    }

    private suspend fun tryLoadingLaunchPad(id: String) : SpaceXLaunchpad? {
        return try {
            spaceXLaunchpadRepository.getLaunchPadById(id)
        } catch (t: Throwable) {
            null
        }
    }
}