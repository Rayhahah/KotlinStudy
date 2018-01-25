package com.rayhahah.kotlinstudy.learn.highFunction

/**
 * 闭包的学习咯
 */
fun makeFun(): () -> Unit {
    var count = 0
    return fun() {
        println(++count)
    }
}

fun fibonacci(): () -> Long {
    var first = 0L
    var second = 1L
    return fun(): Long {
        val result = second
        second += first
        first = second - first
        return result
    }
}


fun testClosure() {
    /**
     * 如此一来
     * 会输出1,2,3,4
     * 函数内的状态被保留，就是闭包了
     */
    val makeFun = makeFun()
    makeFun()
    makeFun()
    makeFun()
    makeFun()
}

