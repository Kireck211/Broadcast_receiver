package iteso.mx.brre

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import android.telephony.TelephonyManager
import androidx.core.app.NotificationCompat.getExtras
import android.os.Bundle



class PowerConnectionReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        if (intent != null) {
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val isCharging =
                status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
            val chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
            val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
        }
    }
}

class BatteryReceiver: BroadcastReceiver() {
    var scale = -1
    var level = -1
    var voltage = -1
    var temp = -1

    override fun onReceive(p0: Context?, intent: Intent?) {
        if (intent != null) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1)
            voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
            Log.e("TAG", "level is " + level + "/" + scale +
                    ", temp is " + temp + ", voltage is " + voltage)
        }
    }
}

class MyPhoneReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val extras = intent.extras
            if (extras != null) {
                val state = extras.getString(TelephonyManager.EXTRA_STATE, "")
                Log.w("MY_DEBUG_TAG", state)
                if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                    val phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    Log.w("MY_DEBUG_TAG", phoneNumber!!)
                }
            }
        }
    }
}




