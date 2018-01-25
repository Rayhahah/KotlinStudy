package com.rayhahah.kotlinstudygradle.dsl

import com.rayhahah.kotlinstudy.learn.dsl.Node
import com.rayhahah.kotlinstudy.learn.dsl.Tag

/**
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/1/25
 * @fuction
 */

fun Html(block: Tag.() -> Unit): String {
    return Tag("html").apply { block(this) }
            .render()
}

fun Tag.head(block: Head.() -> Unit) {
     this + Head().apply { block(this) }

}

class StringNode(val content: String) : Node {
    override fun render() = content
}

class Head : Tag("head") {
    var id by MapDelegate(proerties)
    var _class by MapDelegate(proerties)
}