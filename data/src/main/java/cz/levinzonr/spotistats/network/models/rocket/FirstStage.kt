package cz.levinzonr.spotistats.network.models.rocket

data class FirstStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Int,
    val reusable: Boolean,
    val thrust_sea_level: ThrustSeaLevel,
    val thrust_vacuum: ThrustVacuum
)