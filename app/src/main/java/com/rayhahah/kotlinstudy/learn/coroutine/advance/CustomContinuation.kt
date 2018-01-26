package com.rayhahah.kotlinstudy.learn.coroutine.advance

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

/**
 * 包装类，用来处理线程调度的工作
 */
class UiContinuationWrapper<T>(val continuation: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext = continuation.context

    override fun resume(value: T) {
        continuation.resume(value)
    }

    override fun resumeWithException(exception: Throwable) {
        continuation.resumeWithException(exception)
    }
}

class ContextContinuation<T>(override val context: CoroutineContext = EmptyCoroutineContext) : Continuation<T> {
    override fun resume(value: T) {
    }

    override fun resumeWithException(exception: Throwable) {
    }
}
