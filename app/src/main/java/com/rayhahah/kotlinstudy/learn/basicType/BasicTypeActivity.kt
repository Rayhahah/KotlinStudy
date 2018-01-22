package com.rayhahah.kotlinstudy.learn.basicType

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rayhahah.kotlinstudy.R
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
    }

    /**
     * 数组和区间
     */
    private fun arrayAndRange() {
        //下面两个是等价的
        l("${100 in range}")
        l("${range.contains(100)}")
        //        emptyRange.contains()任何都是true

        val arrayOfInt: IntArray = intArrayOf(1, 3, 5, 7)
        val arrayOfChar: CharArray = charArrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
        val arrayOfString: Array<String> = arrayOf("我", "是", "码农")
        val arrayOf书记: Array<Person> = arrayOf(Person("章", 10), Person("赵", 20), Person("黄", 30))
        for (int in arrayOfInt) {
            println(int)
        }
        println(arrayOf书记[1])
        arrayOf书记[1] = Person("方", 40)
        println(arrayOf书记[1])

        //连接成一个String,输入参数就是链接符号
        println(arrayOfChar.joinToString())
        //获取第二和第三个元素，也就是获取区间内的元素
        println(arrayOfInt.slice(1..2))
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
