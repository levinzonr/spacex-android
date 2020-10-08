package cz.levinzonr.spotistats.presentation.screens.main.images

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import cz.levinzonr.spotistats.presentation.R
import kotlinx.android.synthetic.main.view_images.view.*
import timber.log.Timber

class ImagesView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    private lateinit var adapter: ImagesAdapter

    init {
        View.inflate(context, R.layout.view_images, this)
        adapter = ImagesAdapter()
        imagesRv.adapter = adapter
    }

    fun submitImages(list: List<String>) {
        Timber.d("Submit $list")
        adapter.submitList(list)
    }
}