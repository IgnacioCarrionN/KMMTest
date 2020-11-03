package dev.carrion.local.di

import dev.carrion.local.DriverFactory
import dev.carrion.local.KMMTestQueries
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

actual val localModule: DI.Module = DI.Module("localModule") {
    bind<KMMTestQueries>() with singleton { DriverFactory(instance()).cache().kMMTestQueries }
}
