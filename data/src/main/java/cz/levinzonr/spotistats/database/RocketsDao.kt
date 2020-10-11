package cz.levinzonr.spotistats.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RocketsDao : BaseDao<RocketEntity>{
    @Query("SELECT * FROM ROCKETENTITY WHERE id=:id")
    fun findById(id: String): RocketEntity?

    @Query("SELECT * FROM ROCKETENTITY")
    fun findAll() : List<RocketEntity>
}