package cz.levinzonr.spotistats.presentation.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import cz.levinzonr.spotistats.presentation.R

fun ImageView.loadWithPlaceholder(
    uri: String?,
    @DrawableRes placeholder: Int = R.drawable.image_placeholder
) {
    load(uri) {
        placeholder(placeholder)
        error(placeholder)
        fallback(placeholder)
    }
}