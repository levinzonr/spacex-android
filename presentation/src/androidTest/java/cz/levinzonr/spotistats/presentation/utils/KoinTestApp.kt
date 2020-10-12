package cz.levinzonr.spotistats.presentation.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class KoinTestApp :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinTestApp)
            modules(listOf())
        }
    }

    internal fun inject(module: Module) {
        loadKoinModules(module)
    }
}