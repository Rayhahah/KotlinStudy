package com.rayhahah.kotlinstudygradle.dsl

import com.rayhahah.kotlinstudy.learn.dsl.Tag

/**
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/1/25
 * @fuction
 */

fun main(args: Array<String>) {
    Tag("html").apply {
        proerties.put("id", "123")
        children.add(Tag("head"))
    }.render().let(::println)

    //和上面是一样的
    Html {
        proerties.put("id", "234")
        children.add(Tag("head"))
    }.let(::println)

    Html {
        "id"("567")
        head {
            id = "bodyId"
            _class = "bodyClass"
            "width"("500")
            "title"{
                +"hello DSL"
            }
        }
    }.let(::println)
}