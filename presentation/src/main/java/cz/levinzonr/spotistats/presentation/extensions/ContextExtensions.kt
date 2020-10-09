package cz.levinzonr.spotistats.presentation.extensions

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Activity.hideKeyboard() {
    val view = currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

val Fragment.supportActionBar: ActionBar?
    get() = (activity as? AppCompatActivity?)?.supportActionBar

fun View.dpToPx(dp: Int): Int {
    return context.dpToPx(dp)
}

fun ViewGroup.inflate(@LayoutRes id: Int): View {
    return LayoutInflater.from(context).inflate(id, this, false)
}

fun Fragment.dpToPx(dp: Int): Int {
    return requireContext().dpToPx(dp)
}

fun Context.dpToPx(dp: Int): Int {
    return (dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Context.openBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply { data = url.toUri() }
    startActivity(intent)
}

fun Fragment.openBrowser(url: String) {
    context?.openBrowser(url)
}
