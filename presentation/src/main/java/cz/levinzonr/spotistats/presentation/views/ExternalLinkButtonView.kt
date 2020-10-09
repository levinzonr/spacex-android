package cz.levinzonr.spotistats.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import cz.levinzonr.spotistats.presentation.R
import kotlinx.android.synthetic.main.view_external_link_button_view.view.*

class ExternalLinkButtonView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    init {
        View.inflate(context, R.layout.view_external_link_button_view, this)
    }

    fun setup(title: String, @DrawableRes imageResource: Int, onClick: () -> Unit) {
        linkButtonImageView.setImageResource(imageResource)
        linkButtonTextView.text = title
        root?.setOnClickListener { onClick.invoke() }
    }
}