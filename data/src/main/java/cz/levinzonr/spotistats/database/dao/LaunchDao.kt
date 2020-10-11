package cz.levinzonr.spotistats.database.dao

import androidx.room.Dao
import androidx.room.Query
import cz.levinzonr.spotistats.database.entity.LaunchEntity

@Dao
interface LaunchDao : BaseDao<LaunchEntity> {
    @Query("SELECT * FROM LAUNCHENTITY WHERE id=:id")
    fun findById(id: String): LaunchEntity?

    @Query("SELECT * FROM LaunchEntity")
    fun findAll() : List<LaunchEntity>
}