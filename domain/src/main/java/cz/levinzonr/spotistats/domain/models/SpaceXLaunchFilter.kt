package cz.levinzonr.spotistats.domain.models

import java.util.*

data class SpaceXLaunchFilter(
    val rocketsIds: List<String> = listOf(),
    val interval: DateInterval? = null
)

data class DateInterval(val startDate: Date, val endDate: Date) {

    fun isInInterval(date: Date?) : Boolean {
        return if (date == null) {
            false
        } else {
            return date in startDate..endDate
        }
    }
}