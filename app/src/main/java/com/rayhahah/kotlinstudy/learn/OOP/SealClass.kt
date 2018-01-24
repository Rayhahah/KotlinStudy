package com.rayhahah.kotlinstudy.learn.OOP


/**
 * 只能在相同文件中去继承密封类
 */
sealed class PlayCMD {

}

class PlayCD : PlayCMD()
object StopCD : PlayCMD()