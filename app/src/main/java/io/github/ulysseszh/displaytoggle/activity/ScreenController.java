package com.example.screenoff;

import android.os.IBinder;
import java.lang.reflect.Method;

public class ScreenController {

    public static void turnOff() {
        try {
            Class<?> sc = Class.forName("android.view.SurfaceControl");

            long[] ids = (long[]) sc.getMethod("getPhysicalDisplayIds").invoke(null);
            if (ids == null) return;

            Method getToken = sc.getMethod("getPhysicalDisplayToken", long.class);
            Method setPower = sc.getMethod("setDisplayPowerMode", IBinder.class, int.class);

            for (long id : ids) {
                IBinder token = (IBinder) getToken.invoke(null, id);
                setPower.invoke(null, token, 0);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void turnOn() {
        try {
            Class<?> sc = Class.forName("android.view.SurfaceControl");

            long[] ids = (long[]) sc.getMethod("getPhysicalDisplayIds").invoke(null);
            if (ids == null) return;

            Method getToken = sc.getMethod("getPhysicalDisplayToken", long.class);
            Method setPower = sc.getMethod("setDisplayPowerMode", IBinder.class, int.class);

            for (long id : ids) {
                IBinder token = (IBinder) getToken.invoke(null, id);
                setPower.invoke(null, token, 2);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
