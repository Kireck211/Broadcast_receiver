package iteso.mx.brre

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var batteryReceiver: BatteryReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        batteryReceiver = BatteryReceiver()
        registerReceiver(batteryReceiver, intentFilter)
    }


    override fun onStop() {
        super.onStop()
        unregisterReceiver(batteryReceiver)
    }
}

