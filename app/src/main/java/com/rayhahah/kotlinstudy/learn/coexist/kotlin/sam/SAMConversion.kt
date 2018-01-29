package cn.kotliner.kotlin.sam

import com.rayhahah.kotlinstudy.learn.coexist.java.sam.SAMInJava


fun main(args: Array<String>) {
    val samInJava = SAMInJava()
    val lambda = {
        println("Hello")
    }

    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)

    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)

    val samInKotlin = SAMInKotlin()
    samInKotlin.addTask {

    }
}