package io.github.ulysseszh.displaytoggle.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.github.ulysseszh.displaytoggle.Constants.ACTION_SET_DISPLAY_POWER_MODE
import io.github.ulysseszh.displaytoggle.util.Display.PowerMode
// XÓA TẤT CẢ CÁC DÒNG IMPORT ScreenController CŨ

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

        // ... các đoạn check powerMode giữ nguyên ...

        Thread {
            val isOn = intent.getBooleanExtra("on", true)
            
            // Cách gọi đúng và sạch sẽ nhất:
            if (isOn) {
                ScreenController.turnOn()
            } else {
                ScreenController.turnOff()
            }
            
            finish()
        }.start()
    }
}
