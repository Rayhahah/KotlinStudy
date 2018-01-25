package com.rayhahah.kotlinstudy.learn.highFunction

import com.rayhahah.kotlinstudy.l

/**
 * 高阶函数的学习与使用
 */
fun useHigherFunction() {
    val dataList = listOf<Int>(1, 2, 3, 4, 5, 6)
    //生成了一个String的一个集合
    var stringList = dataList.map m@ { i ->
        val result = "i=$i"
        return@m result
    }
    //生成了一个double的一个集合
    var doubleList = dataList.map { Int::toDouble }


    val dataList2 = listOf(1..20, 40..50, 90..100)

    /**
     * 和Map类似，只不过每次迭代返回的是一个Iterable
     */
    var stringListFlatmap = dataList2.flatMap { intRange: IntRange ->
        intRange.map { intElement: Int ->
            "No.$intElement"
        }
    }
    /**
     * acc：上次返回的结果
     * i：本次迭代的元素
     */
    //结果就是=1+2+3+4+5+6
    var intReduce = dataList.reduce { acc, i ->
        acc + i
    }

    /**
     * 遍历dataList阶乘结果的一个集合
     */
    var jiechengList = dataList.map { ::jiecheng }

    /**
     * 有初始化值的reduce，可以完全替代reduce的使用
     * 得到 1,2,3,4,5,6 的一个StringBuffer
     */
    var stringBufferFold = dataList.fold(StringBuffer()) { acc: StringBuffer, i: Int ->
        acc.append("$i,")
    }


    /**
     * lambda返回true，那么当前迭代的元素就加入结果集合
     * 下面表示得到一个基数集合
     */
    var filterList = dataList.filter { i: Int -> i % 2 == 1 }

    /**
     * 遇到第一个return false的情况就中断
     * 返回前面构成的集合
     * 下面的结果就是 [1,2,3,4]
     */
    var takeWhileList = dataList.takeWhile { i: Int -> i != 5 }


    /**
     * 调用者在内部，就可以避免了繁琐的  ?.
     * 就等于把调用这传入到表达式当中
     */
    findPerson()?.let { (name, age) ->
        //这个是数据类data的特性，component属性，参考前面的内容吧
        l(name)
        l(age)
    }

    var applyPerson = findPerson()?.apply {
        l(name)
        l(age)
    }
}

/**
 * 使用Reduce轻松实现阶乘
 */
fun jiecheng(n: Int): Int {
    if (n == 0) return 1
    return (1..n).reduce { acc, i -> acc * i }
}

data class Person(val name: String, val age: Int)

fun findPerson(): Person? {
    return null
}