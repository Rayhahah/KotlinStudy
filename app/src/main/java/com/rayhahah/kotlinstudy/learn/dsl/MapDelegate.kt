package com.rayhahah.kotlinstudygradle.dsl

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/1/25
 * @fuction
 */

class MapDelegate(val map: MutableMap<String, String>) : ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return map[property.name] ?: ""
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        map[property.name] = value
    }

}