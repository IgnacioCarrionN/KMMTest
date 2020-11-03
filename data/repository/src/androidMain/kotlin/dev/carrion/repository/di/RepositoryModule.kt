package dev.carrion.repository.di

import dev.carrion.local.LocalDataSource
import dev.carrion.local.LocalDataSourceImpl
import dev.carrion.repository.FactRepository
import dev.carrion.repository.FactRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


actual val repositoryModule: DI.Module = DI.Module("RepositoryModule") {
    bind<LocalDataSource>() with singleton { LocalDataSourceImpl(instance()) }

    bind<FactRepository>() with singleton { FactRepositoryImpl(instance(), instance()) }
}