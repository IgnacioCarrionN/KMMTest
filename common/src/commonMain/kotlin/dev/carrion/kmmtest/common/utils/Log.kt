package dev.carrion.kmmtest.common.utils

expect sealed class Log {

    object Debug : Log, Logger

    object Warning : Log, Logger

}


interface Logger {

    fun log(title: String, message: String)

}