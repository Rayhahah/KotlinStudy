package com.rayhahah.kotlinstudy.learn.coroutine.advance

import com.rayhahah.kotlinstudy.l
import com.rayhahah.kotlinstudy.learn.coroutine.basic.AsyncTask
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine


/**
 * 开启协程代码块
 */
fun coro(context: CoroutineContext = EmptyCoroutineContext, block: suspend () -> Unit) {
    block.startCoroutine(ContextContinuation(context + AsyncContext()))
}


/**
 * 开启耗时操作的代码块
 */
suspend fun <T> asy(block: CoroutineContext.() -> T) = suspendCoroutine<T> { continuation ->
    AsyncTask {
        try {
            continuation.resume(block(continuation.context))
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }
}

fun testCoro() {
    l("运行")
    coro {
        //获取返回结果
        var url = asy {}
    }
}