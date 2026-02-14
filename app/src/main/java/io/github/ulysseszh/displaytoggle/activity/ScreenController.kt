package io.github.ulysseszh.displaytoggle.activity // Thống nhất dùng package này

import android.os.IBinder
import java.lang.reflect.Method

object ScreenController {
    // THÊM 2 DÒNG NÀY VÀO
    const val OFF = 0
    const val ON = 2

    fun turnOff() {
        setPowerMode(OFF)
    }

    fun turnOn() {
        setPowerMode(ON)
    }

    // Viết gọn lại hàm setPower để tái sử dụng hằng số
    private fun setPowerMode(mode: Int) {
        try {
            val sc = Class.forName("android.view.SurfaceControl")
            val ids = sc.getMethod("getPhysicalDisplayIds").invoke(null) as? LongArray ?: return
            val getToken: Method = sc.getMethod("getPhysicalDisplayToken", Long::class.javaPrimitiveType)
            val setPower: Method = sc.getMethod("setDisplayPowerMode", IBinder::class.java, Int::class.javaPrimitiveType)

            for (id in ids) {
                val token = getToken.invoke(null, id) as IBinder
                setPower.invoke(null, token, mode)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
