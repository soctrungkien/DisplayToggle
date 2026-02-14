package io.github.ulysseszh.displaytoggle.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.github.ulysseszh.displaytoggle.Constants.ACTION_SET_DISPLAY_POWER_MODE
import io.github.ulysseszh.displaytoggle.util.Display.PowerMode
// CHỈ GIỮ LẠI DÒNG IMPORT NÀY:
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
            // Sử dụng ScreenController (lớp đã import ở trên) để truy cập ON/OFF
            val mode = if (intent.getBooleanExtra("on", true)) ScreenController.ON else ScreenController.OFF
            
            // Lưu ý: Bạn nên thêm logic thực thi mode ở đây trước khi finish()
            // Ví dụ: ScreenController.setDisplayPowerMode(mode) 
            
            finish()
        }.start()
    }
}
