package dev.carrion.kmmtest.common.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

actual val testCoroutineContext: CoroutineContext = object : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        // TODO
    }
}

actual fun runBlockingTest(block: suspend () -> Unit) =
    runBlocking(testCoroutineContext) { block() }