package com.rayhahah.kotlinstudy.learn.OOP

/**
 * 使用委托，来替换掉繁琐的对象调用
 */
class Manager(driver: Driver, writer: Writer) : Driver by driver, Writer by writer {

}

interface Driver {
    fun driver()
}

interface Writer {
    fun write()
}