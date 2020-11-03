package dev.carrion.kmmtest.common.utils

import kotlin.coroutines.CoroutineContext

expect fun runBlockingTest(block: suspend () -> Unit)
expect val testCoroutineContext: CoroutineContext