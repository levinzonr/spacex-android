package cz.levinzonr.spotistats.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.levinzonr.spotistats.cache.base.CachedEntity
import cz.levinzonr.spotistats.domain.models.SpaceXRocket

@Entity
class RocketEntity(
    @PrimaryKey
    override val id: String,
    val name: String,
    val images: List<String>,
    val description: String?
) : CachedEntity() {

    constructor(spaceXRocket: SpaceXRocket) : this(
        id = spaceXRocket.id,
        name = spaceXRocket.name,
        images = spaceXRocket.images,
        description = spaceXRocket.description
    )
}