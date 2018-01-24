package com.rayhahah.kotlinstudy.learn.OOP

/**
 * 伴生对象
 * 也就相对应Java的静态方法和成员
 */
class Latitude private constructor(val value: Double){
    companion object{
        @JvmStatic
        fun ofDouble(double: Double): Latitude{
            return Latitude(double)
        }

        fun ofLatitude(latitude: Latitude): Latitude{
            return Latitude(latitude.value)
        }

        @JvmField
        val TAG: String = "Latitude"
    }
}