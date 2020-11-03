package dev.carrion.kmmtest.androidApp

import android.app.Application
import dev.carrion.kmmtest.androidApp.di.appModule
import dev.carrion.local.di.localModule
import dev.carrion.remote.di.remoteModule
import dev.carrion.repository.di.repositoryModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule


class App : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@App))
        import(appModule)
        import(localModule)
        import(remoteModule)
        import(repositoryModule)
    }
}