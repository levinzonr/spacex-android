package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.DateInterval
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.models.SpaceXLaunchFilter

class FilterLaunchesInteractor : Interactor<FilterLaunchesInteractor.Input, List<SpaceXLaunch>> {

    override suspend fun invoke(input: Input): List<SpaceXLaunch> {
        return input.launches
            .filterByDateInterval(input.filter.interval)
            .filterByRocketIds(input.filter.rocketsIds)
    }

    private fun List<SpaceXLaunch>.filterByDateInterval(dateInterval: DateInterval?) : List<SpaceXLaunch> {
        val interval = dateInterval ?: return this
        return filter { interval.isInInterval(it.date) }
    }

    private fun List<SpaceXLaunch>.filterByRocketIds(ids: List<String>) : List<SpaceXLaunch> {
        return if (ids.isEmpty()) {
            this
        } else {
            filter { ids.contains(it.rocketId) }
        }
    }


    data class Input(val launches: List<SpaceXLaunch>, val filter: SpaceXLaunchFilter)
}