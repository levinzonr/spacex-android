package cz.levinzonr.spotistats.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T) : Long

    @Delete
    fun deleteAll(items: List<T>)

    @Delete
    fun delete(items: List<T>)
}