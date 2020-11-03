package dev.carrion.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class DriverFactory(private val appContext: Context) {
    actual fun cache(): KMMTest {
        val driver = AndroidSqliteDriver(KMMTest.Schema, appContext, "KMMTest.db")
        return KMMTest(driver)
    }
}

