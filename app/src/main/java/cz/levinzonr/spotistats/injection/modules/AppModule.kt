package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.database.databaseModule
import cz.levinzonr.spotistats.presentation.injection.viewModels

val appModule = listOf(
        initilizerModule,
        interactorModule,
        restModule,
        repositoryModule,
        viewModels,
        databaseModule,
        cacheModule,
        managerModule
)