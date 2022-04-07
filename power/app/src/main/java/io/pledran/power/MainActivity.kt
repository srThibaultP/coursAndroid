package io.pledran.power

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val ivBattery by lazy { findViewById<ImageView>(R.id.ivBattery) }

    private val filter by lazy {
        IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_POWER_CONNECTED -> ivBattery.setImageResource(R.drawable.ic_baseline_battery_charging_full)
                Intent.ACTION_POWER_DISCONNECTED -> ivBattery.setImageResource(R.drawable.ic_baseline_battery_full)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(receiver, filter)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initPowerStatus()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)

        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initPowerStatus() {
        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager

        if (batteryManager.isCharging) {
            ivBattery.setImageResource(R.drawable.ic_baseline_battery_charging_full)
        } else {
            ivBattery.setImageResource(R.drawable.ic_baseline_battery_full)
        }
    }
}