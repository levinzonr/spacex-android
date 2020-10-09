package cz.levinzonr.spotistats.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LaunchDao : BaseDao<LaunchEntity>{
    @Query("SELECT * FROM LAUNCHENTITY WHERE id=:id")
    fun findById(id: String): LaunchEntity?

    @Query("SELECT * FROM LaunchEntity")
    fun findAll() : List<LaunchEntity>
}