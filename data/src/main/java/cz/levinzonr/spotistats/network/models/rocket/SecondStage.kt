package cz.levinzonr.spotistats.network.models.rocket

data class SecondStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Int,
    val payloads: Payloads,
    val reusable: Boolean,
    val thrust: Thrust
)