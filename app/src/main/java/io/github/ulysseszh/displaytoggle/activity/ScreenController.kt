package com.example.screenoff

import android.os.IBinder
import java.lang.reflect.Method

object ScreenController {

    fun turnOff() {
        try {
            val sc = Class.forName("android.view.SurfaceControl")

            val ids = sc.getMethod("getPhysicalDisplayIds").invoke(null) as? LongArray ?: return

            val getToken: Method = sc.getMethod("getPhysicalDisplayToken", Long::class.javaPrimitiveType)
            val setPower: Method = sc.getMethod("setDisplayPowerMode", IBinder::class.java, Int::class.javaPrimitiveType)

            for (id in ids) {
                val token = getToken.invoke(null, id) as IBinder
                setPower.invoke(null, token, 0)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun turnOn() {
        try {
            val sc = Class.forName("android.view.SurfaceControl")

            val ids = sc.getMethod("getPhysicalDisplayIds").invoke(null) as? LongArray ?: return

            val getToken: Method = sc.getMethod("getPhysicalDisplayToken", Long::class.javaPrimitiveType)
            val setPower: Method = sc.getMethod("setDisplayPowerMode", IBinder::class.java, Int::class.javaPrimitiveType)

            for (id in ids) {
                val token = getToken.invoke(null, id) as IBinder
                setPower.invoke(null, token, 2)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
