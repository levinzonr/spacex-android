package cz.levinzonr.spotistats.domain.models

class SpaceXLinks(
    val youtubeId: String?,
    val wikiPage: String?,
    val articlePage: String?
) {
    val youtubeLink: String?
        get() = youtubeId?.let { id -> "https://www.youtube.com/watch?v=$id" }
}