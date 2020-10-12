package cz.levinzonr.spotistats.presentation.utils

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import org.hamcrest.CoreMatchers.not

fun <V : View> ViewInteraction.checkViewAs(block: (view: V) -> Unit): ViewInteraction {
    return check { view, _ -> block(view as V) }
}

fun verifyExceptionThrown(block: () -> Unit) {
    try {
        block.invoke()
        assert(false)
    } catch (e: Throwable) {
        assert(true)
    }
}

fun onViewWithId(id: Int): ViewInteraction = Espresso.onView(withId(id))

fun ViewInteraction.performClick(): ViewInteraction {
    return perform(ViewActions.click())
}

fun ViewInteraction.checkVisibility(visibility: ViewMatchers.Visibility): ViewInteraction {
    return check(matches(ViewMatchers.withEffectiveVisibility(visibility)))
}

fun ViewInteraction.checkTextMatches(text: String): ViewInteraction {
    return check(matches(withText(text)))
}

fun ViewInteraction.checkTextEmpty(): ViewInteraction {
    return checkTextMatches("")
}

fun ViewInteraction.typeText(text: String): ViewInteraction {
    return perform(ViewActions.typeText(text))
}

fun ViewInteraction.checkEnabled(): ViewInteraction {
    return check(matches(isEnabled()))
}

fun ViewInteraction.checkDisabled(): ViewInteraction {
    return check(matches(not(isEnabled())))
}

