package io.github.ulysseszh.displaytoggle.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.github.ulysseszh.displaytoggle.Constants.ACTION_SET_DISPLAY_POWER_MODE
import io.github.ulysseszh.displaytoggle.util.Display.PowerMode
// Dòng import quan trọng nhất:
import io.github.ulysseszh.displaytoggle.ScreenController

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
            // Sử dụng trực tiếp ScreenController.ON/OFF thông qua lớp đã import
            val mode = if (intent.getBooleanExtra("on", true)) {
                ScreenController.ON
            } else {
                ScreenController.OFF
            }
            
            // Thực thi lệnh shell (giả sử ScreenController có hàm shell hoặc logic tương ứng)
            // ScreenController.setDisplayPowerMode(mode)
            
            finish()
        }.start()
    }
}
