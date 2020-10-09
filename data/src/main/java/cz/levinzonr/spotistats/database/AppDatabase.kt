
package cz.levinzonr.spotistats.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LaunchEntity::class], version = 1)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao() : LaunchDao
}