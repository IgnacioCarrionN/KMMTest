package dev.carrion.kmmtest.common.utils

actual sealed class Log {

    actual object Debug : Log(), Logger {
        override fun log(title: String, message: String) {
            // TODO
        }
    }

    actual object Warning : Log(), Logger {
        override fun log(title: String, message: String) {
            // TODO
        }
    }
}