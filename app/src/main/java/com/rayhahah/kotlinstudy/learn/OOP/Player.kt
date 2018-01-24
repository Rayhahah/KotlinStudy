package com.rayhahah.kotlinstudy.learn.OOP

interface Music {
    fun funny()
}

abstract class Player

/**
 * Object是一种特殊的class
 * 等于默认实现了单例模式的class
 */
object MusicPlayer : Player(), Music {
    override fun funny() {
    }

    fun play(url: String) {

    }

    fun stop() {}

}