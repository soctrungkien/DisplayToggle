package io.github.ulysseszh.displaytoggle.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.github.ulysseszh.displaytoggle.Constants.ACTION_SET_DISPLAY_POWER_MODE
import io.github.ulysseszh.displaytoggle.util.Display.PowerMode
import io.github.ulysseszh.displaytoggle.activity.ScreenController

class SetDisplayPowerModeActivity : Activity() {

    companion object {
        private val TAG = SetDisplayPowerModeActivity::class.simpleName!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action != ACTION_SET_DISPLAY_POWER_MODE) {
            Log.e(TAG, "Unsupported action: ${intent.action}")
            finish()
            return
        }

        val powerModeString = intent.getStringExtra("POWER_MODE")
        val powerMode = PowerMode.fromString(powerModeString)

        if (powerMode == null) {
            Log.e(TAG, "Unsupported mode: $powerModeString")
            finish()
            return
        }

        Thread {
            when (powerMode) {
                PowerMode.OFF -> ScreenController.turnOff()
                PowerMode.ON -> ScreenController.turnOn()
                else -> Log.e(TAG, "Unhandled power mode: $powerMode")
            }
            finish()
        }.start()
    }
}
