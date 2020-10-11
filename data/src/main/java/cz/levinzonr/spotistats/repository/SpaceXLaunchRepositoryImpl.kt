package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.cache.base.CachingStrategy
import cz.levinzonr.spotistats.database.dao.LaunchDao
import cz.levinzonr.spotistats.database.entity.LaunchEntity
import cz.levinzonr.spotistats.domain.models.SpaceXLaunch
import cz.levinzonr.spotistats.domain.repository.SpaceXLaunchRepository
import cz.levinzonr.spotistats.mappers.LaunchEntityMapper
import cz.levinzonr.spotistats.mappers.LaunchResponseMapper
import cz.levinzonr.spotistats.mappers.mapWithMapper
import cz.levinzonr.spotistats.network.Api
import java.util.*

class SpaceXLaunchRepositoryImpl(
    private val api: Api,
    private val localDataSource: LaunchDao,
    private val cachingStrategy: CachingStrategy<LaunchEntity>,
    private val listCachingStrategy: CachingStrategy<List<LaunchEntity>>
) : SpaceXLaunchRepository {
    override suspend fun getPastLaunches(): List<SpaceXLaunch> {
        return listCachingStrategy
            .setRemoteSource { api.getPastLaunches().mapWithMapper(LaunchResponseMapper).map { it.toEntity() }}
            .setOnUpdateItems { localDataSource.insertAll(it) }
            .setCachingSource { loadPastLaunchesFromCache() }
            .apply().mapWithMapper(LaunchEntityMapper)
    }

    override suspend fun getUpcomingLaunches(): List<SpaceXLaunch> {
        return listCachingStrategy
            .setRemoteSource { api.getUpcomingLaunches()
                .mapWithMapper(LaunchResponseMapper).map { it.toEntity() }}
            .setOnUpdateItems { localDataSource.insertAll(it) }
            .setCachingSource { loadUpcomingFromCache() }
            .apply().mapWithMapper(LaunchEntityMapper)
    }

    override suspend fun getLaunchById(id: String): SpaceXLaunch {
        return cachingStrategy
            .setRemoteSource { LaunchResponseMapper.toDomain(api.getLaunchById(id)).toEntity() }
            .setOnUpdateItems {localDataSource.insert(it) }
            .setCachingSource { localDataSource.findById(id) }
            .apply().let { LaunchEntityMapper.toDomain(it) }
    }

    private  fun loadUpcomingFromCache() : List<LaunchEntity> {
        return localDataSource.findAll().filter { it.date >= Date() }
    }

    private  fun loadPastLaunchesFromCache() : List<LaunchEntity> {
        return localDataSource.findAll().filter { it.date < Date() }
    }

    private fun SpaceXLaunch.toEntity() : LaunchEntity {
        return LaunchEntity(
            id = id,
            imagesUrls = imagesUrls,
            thumbnail = thumbnail,
            date = date,
            name = name,
            crewMembersIds = crewMembersIds,
            rocketId = rocketId,
            youtubeId = links.youtubeId,
            articlePageUrl = links.articlePage,
            details = details,
            wikiPageUrl = links.wikiPage,
            launchpadId = launchpadId
        )
    }
}