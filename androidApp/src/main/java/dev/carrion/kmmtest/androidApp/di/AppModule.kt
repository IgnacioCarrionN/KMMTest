package dev.carrion.kmmtest.androidApp.di

import dev.carrion.kmmtest.androidApp.home.MainViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val appModule = DI.Module("appModule") {
    bind<MainViewModel.Factory>() with provider { MainViewModel.Factory(instance()) }
}