package com.rayhahah.kotlinstudy.learn.OOP

import com.rayhahah.kotlinstudy.l

/**
 * 账户要了解继承与实现的东西
 */
interface InputDevice {
    fun input(key: Any) {
        l("device key=$key")
    }
}

interface USBInputDevice : InputDevice

interface BLEInputDevice : InputDevice

open class USBMouse : USBInputDevice {
    override fun input(key: Any) {
    }
}

class AppleMouse : USBMouse() {

}


class Computer {
    fun addInputDevice(inputDevice: InputDevice) {
        when (inputDevice) {
            is BLEInputDevice -> addBLEInputDevice(inputDevice)
            is USBInputDevice -> addUSBInputDevice(inputDevice)
            else -> throw IllegalArgumentException()
        }
    }

    fun addUSBInputDevice(usbInputDevice: USBInputDevice) {


    }

    fun addBLEInputDevice(bleInputDevice: BLEInputDevice) {


    }
}
