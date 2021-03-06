package cz.levinzonr.spotistats

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import cz.levinzonr.spotistats.inititializers.AppInitializer
import cz.levinzonr.spotistats.injection.modules.appModule
import cz.levinzonr.roxie.Roxie
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    private val initializer: AppInitializer by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(appModule)

        }
        Roxie.enableLogging(object : Roxie.Logger {
            override fun log(msg: String) {
                Timber.d(msg)
            }
        })
        initializer.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}