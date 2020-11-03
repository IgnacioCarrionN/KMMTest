package dev.carrion.remote.di

import dev.carrion.remote.FactApi
import io.ktor.client.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

actual val remoteModule = DI.Module("remoteModule"){
    // TODO Implement iOS HttpClient()
    bind<HttpClient>() with singleton { HttpClient() }

    bind<FactApi>() with singleton { FactApi(instance()) }
}