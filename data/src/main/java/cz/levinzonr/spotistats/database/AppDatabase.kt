
package cz.levinzonr.spotistats.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.levinzonr.spotistats.database.converters.DateConverter
import cz.levinzonr.spotistats.database.dao.LaunchDao
import cz.levinzonr.spotistats.database.dao.RocketsDao
import cz.levinzonr.spotistats.database.converters.StringListConverter
import cz.levinzonr.spotistats.database.entity.LaunchEntity
import cz.levinzonr.spotistats.database.entity.RocketEntity

@Database(entities = [LaunchEntity::class, RocketEntity::class], version = 1)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao() : LaunchDao
    abstract fun rocketsDao() : RocketsDao
}