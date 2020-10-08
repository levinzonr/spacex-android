package cz.levinzonr.spotistats.domain.models

data class SpaceXLaunchDetails(
    val spaceXLaunchpad: SpaceXLaunchpad?,
    val crew: List<SpaceXCrewMember>,
    val rocket: SpaceXRocket?,
    val launch: SpaceXLaunch
)