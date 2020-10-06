package cz.levinzonr.spotistats.network.models.rocket

data class PayloadWeight(
    val id: String,
    val kg: Int,
    val lb: Int,
    val name: String
)