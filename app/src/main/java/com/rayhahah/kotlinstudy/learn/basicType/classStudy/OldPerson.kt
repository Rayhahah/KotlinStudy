package com.rayhahah.kotlinstudy.learn.basicType.classStudy

/**
 * 需要在从父类继承下来的构造器中指定需要的参数。这是用来替换Java中的super调用的。
 */
class OldPerson(name: String, sex: Boolean) : OpenPerson(name, sex) {

}