package com.rayhahah.kotlinstudy.learn.highFunction

/**
 * 函数引用的详细使用
 */
fun main(args: Array<String>) {
    args.forEach(::println)

    val helloWorld = Hello::world

    /**
     * 拓展函数，类函数的第一个传入参数都是类本身，所以这里可以使用
     */
    args.filter(String::isNotEmpty)

    /**
     * 使用对象函数的话就没有上面存在的问题，所以可以引用
     */
    val pdfPrinter = PdfPrinter()
    args.forEach(pdfPrinter::println)
}

class PdfPrinter{
    fun println(any: Any){
        kotlin.io.println(any)
    }
}

class Hello{
    fun world(){
        println("Hello World.")
    }
}