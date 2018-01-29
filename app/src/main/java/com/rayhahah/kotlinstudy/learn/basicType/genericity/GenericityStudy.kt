package com.rayhahah.kotlinstudy.learn.basicType.genericity

/**
 * Kotlin中的泛型学习
 */

/**
 * 作为声明类型的时候
 * in ： 表示只能作为参数传入
 * out : 表示只能作为返回值返回
 */
interface Eating<in T, out E> {
    fun eat(shit: T): E
}

fun testGenericity() {
    var phoneArray = arrayOf<Phone>()
    var chinaPhoneArray = arrayOf<ChinaPhone>()
    var miPhoneArray = arrayOf<MIPhone>()

// 编译报错，提示需要Array<Any>,而参数是Array<String>类型


    /**
     *编译不通过，basicArray必须是Phone类型
     */
//    copyGener(chinaPhoneArray, miPhoneArray, phoneArray)
    /**
     *编译不通过，outArray必须是ChinaPhone的子类或者本类
     */
//    copyGener(phoneArray, phoneArray, phoneArray)
    /**
     *编译不通过，inArray必须是ChinaPhone的父类或者本类
     */
//    copyGener(phoneArray, miPhoneArray, miPhoneArray)

    /**
     * 编译通过
     */
    copyGener(phoneArray, miPhoneArray, phoneArray)
}


/**
 * 形参的in和out
 * 无表示就表示必须是当前参数类型才可以
 * in ： 表示传入参数必须是  类型的本类或者父类
 * out : 表示传入参数必须是 类型的本类或者子类
 */
fun copyGener(basicArray: Array<Phone>, outArray: Array<out ChinaPhone>, inArray: Array<in ChinaPhone>) {
}

//泛型约束类，和Java的<T extends Object>一样的用法
//通过where,可以指定多个上边界
fun <T> cloneWhenGreater(list: List<T>, threshold: T): List<T>?
        where T : Iterable<Int>,
              T : Cloneable {
    return null
}

open class Phone
open class ChinaPhone : Phone()
open class MIPhone : ChinaPhone()
