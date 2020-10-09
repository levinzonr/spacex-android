package cz.levinzonr.spotistats.domain.extensions

import cz.levinzonr.spotistats.domain.interactors.CompleteResult
import cz.levinzonr.spotistats.domain.interactors.Fail
import cz.levinzonr.spotistats.domain.interactors.Success
import java.text.SimpleDateFormat
import java.util.*

inline fun <T> T.guard(block: T.() -> Unit): T {
    if (this == null) block(); return this
}


fun Date.format(format: String = "DD MMM YYY") : String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}