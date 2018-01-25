package com.rayhahah.kotlinstudygradle

import java.io.File

/**
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/1/25
 * @fuction
 */


fun main(args: Array<String>) {
    var map = HashMap<Char, Int>()
    File("build.gradle").readText().toCharArray().filterNot(Char::isWhitespace).forEach {
        var count = map[it]
        if (count == null) {
            map[it] = 1
        } else {
            map[it] = count + 1
        }
    }
//    map.forEach(::println)


    /**
     * 下面的功能与上面是一值的
     */
    File("build.gradle").readText().toCharArray().filterNot(Char::isWhitespace).groupBy { it }.map {
        it.key to it.value.size
    }.forEach(::println)
}