package dev.carrion.remote.di

import dev.carrion.remote.FactApi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

actual val remoteModule = DI.Module("remoteModule") {
    bind<HttpClient>() with singleton {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
    bind<FactApi>() with singleton {
        FactApi(instance())
    }
}