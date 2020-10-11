package cz.levinzonr.spotistats.database

import androidx.room.Room
import cz.levinzonr.spotistats.cache.base.CachingConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database").build()
    }
    single { get<AppDatabase>().launchDao() }

    single { get<AppDatabase>().rocketsDao()     }

}