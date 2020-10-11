package cz.levinzonr.spotistats.presentation.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.transform.Transformation
import cz.levinzonr.spotistats.presentation.R

fun ImageView.loadWithPlaceholder(
    uri: String?,
    @DrawableRes placeholder: Int = R.drawable.image_placeholder,
    transformation: Transformation? = null
) {
    load(uri) {
        placeholder(placeholder)
        error(placeholder)
        fallback(placeholder)
        transformation?.let { transformations(it) }
    }
}