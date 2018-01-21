package cn.kotliner.kotlin.box

import com.rayhahah.kotlinstudy.demo.chapter8.java.box.BoxIf1

/**
 * Created by benny on 5/28/17.
 */
class BoxImpl1: BoxIf1 {
    override fun get(key: Int): String {
        return "Hello"
    }

//    override fun get(key: Int?): String {
//        return "Hello"
//    }
}