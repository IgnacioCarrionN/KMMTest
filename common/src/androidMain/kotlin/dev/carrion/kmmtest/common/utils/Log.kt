package dev.carrion.kmmtest.common.utils

actual sealed class Log {

    actual object Debug : Log(), Logger {
        override fun log(title: String, message: String) {
            android.util.Log.d(title, message)
        }
    }

    actual object Warning : Log(), Logger {
        override fun log(title: String, message: String) {
            android.util.Log.w(title, message)
        }
    }
}