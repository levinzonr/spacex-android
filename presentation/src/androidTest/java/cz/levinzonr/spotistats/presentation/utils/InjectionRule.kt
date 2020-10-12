package cz.levinzonr.spotistats.presentation.utils

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.module.Module

abstract class InjectionRule : TestWatcher() {

    abstract val module: Module

    override fun starting(description: Description?) {
        super.starting(description)
        val application = InstrumentationRegistry.getInstrumentation()
            .targetContext.applicationContext as KoinTestApp
        application.inject(module)
    }

}

internal fun createInjectionRule(module: Module) : InjectionRule {
    return object : InjectionRule() {
        override val module: Module
            get() = module
    }
}