package cz.levinzonr.spotistats.network.models.crew

data class CrewResponse(
    val agency: String?,
    val id: String,
    val image: String?,
    val launches: List<String>,
    val name: String?,
    val status: String?,
    val wikipedia: String?
)