package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.database.entity.RocketEntity
import cz.levinzonr.spotistats.database.dao.RocketsDao
import cz.levinzonr.spotistats.domain.models.SpaceXRocket
import cz.levinzonr.spotistats.domain.repository.SpaceXRocketsRepository
import cz.levinzonr.spotistats.mappers.RocketEntityMapper
import cz.levinzonr.spotistats.mappers.RocketResponseMapper
import cz.levinzonr.spotistats.mappers.mapWithMapper
import cz.levinzonr.spotistats.network.Api

class SpaceXRocketRepositoryImpl(
    private val api: Api,
    private val localDataSource: RocketsDao,
    private val cachingStrategy: CachingStrategy<RocketEntity>,
    private val listCachingStrategy: CachingStrategy<List<RocketEntity>>
    ) : SpaceXRocketsRepository {

    override suspend fun getAllRockets(): List<SpaceXRocket> {
        return listCachingStrategy
            .setRemoteSource { api.getRockets().mapWithMapper(RocketResponseMapper).map { RocketEntity(it) } }
            .setCachingSource { localDataSource.findAll()  }
            .setOnUpdateItems { localDataSource.insertAll(it) }
            .apply()
            .mapWithMapper(RocketEntityMapper)
    }

    override suspend fun getRocketById(id: String): SpaceXRocket {
        return cachingStrategy
            .setRemoteSource { RocketResponseMapper.toDomain(api.getRocketById(id)).let { RocketEntity(it) } }
            .setCachingSource { localDataSource.findById(id) }
            .setOnUpdateItems { localDataSource.insert(it) }
            .apply()
            .let { RocketEntityMapper.toDomain(it) }
    }

}