package cz.levinzonr.spotistats.presentation.views.images

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import cz.levinzonr.spotistats.presentation.R
import kotlinx.android.synthetic.main.view_images.view.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import timber.log.Timber

class ImagesView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    init {
        View.inflate(context, R.layout.view_images, this)
    }

    fun submitImages(list: List<String>) {
        carousel.addData(
            list.map {
                CarouselItem(
                    imageUrl = it
                )
            }
        )
    }
}