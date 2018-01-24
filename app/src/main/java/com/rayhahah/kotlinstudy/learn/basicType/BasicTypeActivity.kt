package com.rayhahah.kotlinstudy.learn.basicType

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rayhahah.kotlinstudy.R
import com.rayhahah.kotlinstudy.forEach
import com.rayhahah.kotlinstudy.hello
import com.rayhahah.kotlinstudy.l
import com.rayhahah.kotlinstudy.learn.basicType.classStudy.OldPerson
import com.rayhahah.kotlinstudy.learn.basicType.classStudy.OpenPerson
import com.rayhahah.kotlinstudy.learn.basicType.classStudy.Person

class BasicTypeActivity : AppCompatActivity() {
    var a = null
    val range: IntRange = 0..1024 //表示区间[0,1024]
    val rangeExclude: IntRange = 0 until 1024 //表示区间[0,1024)
    val emptyRange: IntProgression = 0..-1 //是空的

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_type)
        notNullSafe()
        autoCast()
        arrayAndRange()
        funStudy()
        lambdaStudy()
        whenStudy()
    }

    private fun whenStudy() {
        val args = arrayOf("h", "a", "b")

        val x = 5
        when (x) {
            is Int -> println("Hello $x")
            in 1..100 -> println("$x is in 1..100")
            !in 1..100 -> println("$x is not in 1..100")
            args[0].toInt() -> println("x == args[0]")
        }

        val mode = when {
            args.isNotEmpty() && args[0] == "1" -> 1
            else -> 0
        }
    }

    private fun funStudy() {
        val study = FunctionStudy()
        //这两个调用方法是一样的
        var long = study.long(10)
        var l = study.long.invoke(10)
    }

    /**
     * 数组和区间
     */
    private fun arrayAndRange(): Int {
        //下面两个是等价的
        l("${100 in range}")
        l("${range.contains(100)}")
        //        emptyRange.contains()任何都是true
        for (i in 0..10) {

        }
        val arrayOfInt: IntArray = intArrayOf(1, 3, 5, 7)
        val arrayOfChar: CharArray = charArrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
        val arrayOfString: Array<String> = arrayOf("我", "是", "码农")
        val arrayOf书记: Array<Person> = arrayOf(Person("章", 10), Person("赵", 20), Person("黄", 30))

        for (i in arrayOfInt.size..arrayOfChar.size) {

        }

        for (i in 0 until arrayOfInt.size) {

        }

        /**
         * Iterable的对象都可以使用这种方式来循环
         */
        for ((index, value) in arrayOfInt.withIndex()) {
            l("index=$index,value=$value")
        }
        arrayOfInt.withIndex().forEach {
            l("index=${it.index},value=${it.value}")
        }

        /**
         * 使用标签可以，针对跳出
         */
        var count = 5;
        while (count > 1) {
            count--

            Inner@ for ((index, value) in arrayOfInt.withIndex()) {
                l("index=$index,value=$value")
                continue@Inner
            }
            break

        }



        arrayOf书记[1] = Person("方", 40)
        l("$arrayOf书记[1]")

        //连接成一个String,输入参数就是链接符号
        l(arrayOfChar.joinToString())
        //获取第二和第三个元素，也就是获取区间内的元素
        l("${arrayOfInt.slice(1..2)}")


        return 1
    }

    private fun lambdaStudy() {

        val arrayOfInt: IntArray = intArrayOf(1, 3, 5, 7)

        1 in arrayOfInt

        /**
         * 下面两种方式一样的
         */
        arrayOfInt.forEach({ ele ->
            l("$ele")
        })
        //这个是简写
        arrayOfInt.forEach {
            l("$it")
        }

        /**
         *终极写法
         * 简单来说就是把   l()当成lambda参数传递给forEach使用了
         */
        arrayOfInt.forEach(::l)

        /**
         * 使用标签来return，否则会直接return arrayAndRange()这个函数
         */
        arrayOfInt.forEach f@ {
            if (it == 10) return@f
            l(it)
        }

        //拓展forEach以便更好理解
        //Lambda是最后一个参数就可以从小括号中移出去
        arrayOfInt.forEach(10) { ele ->
            l("$ele")
        }

        /**
         * 匿名函数的类型
         */
        hello {
            it
            return@hello it[0]
        }
    }

    /**
     * 只能转换
     */
    private fun autoCast() {
        var openPerson: OpenPerson = OldPerson("nihao", true, 1)

        if (openPerson is OldPerson) {
            //可以直接使用类型
            openPerson.teach("")
        }


        //as?空安全的类型转换，返回null而不是直接crash应用
        var oldPerson: OldPerson? = openPerson as? OldPerson
        oldPerson?.teach("hh")
    }

    /**
     * 空安全判断
     */
    private fun notNullSafe() {
        var nullNotSafe = getNullNotSafe()

        if (nullNotSafe == null) {
            l("hh")
        } else {
            l("kk")
        }

        nullNotSafe ?: l("hh") ?: l("kk")
        nullNotSafe = ""

        //表示nullNotSafe为空就直接返回退出整个方法
        //        if (nullNotSafe == null) {
        //            return
        //        }
        //和上面是相同的作用
        nullNotSafe ?: return

        //这个是忽略空安全，可能会抛出空异常
        l("${nullNotSafe!!.length}")

        //这个和下面是等价的
        //如果nullNotSafe为口，输出的结果也是null
        l("${nullNotSafe?.length}")
        //java的常用写法
        if (nullNotSafe != null) {
            l("${nullNotSafe.length}")
        }
    }
}

/**
 * 编译器默认不通过，需要在类型上加上？ 表示可以为null
 */
fun getNullNotSafe(): String? {
    return null
}
