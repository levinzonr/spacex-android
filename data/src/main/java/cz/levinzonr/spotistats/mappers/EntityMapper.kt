package cz.levinzonr.spotistats.mappers

interface EntityMapper<DTO, Domain> {
    fun toDomain(dto: DTO) : Domain
}

fun<DTO, Domain> List<DTO>.mapWithMapper(entityMapper: EntityMapper<DTO, Domain>) : List<Domain> {
    return map { entityMapper.toDomain(it) }
}