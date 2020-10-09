package cz.levinzonr.spotistats.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDateToLong(date: Date) : Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(long: Long) : Date {
        return Date(long)
    }
}