package moe.caiomi.tp7batterie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import moe.caiomi.tp7batterie.BuildConfig.DEBUG

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val charging by lazy {findViewById<ImageView>(R.id.batteryUp)}
        val battery by lazy {findViewById<ImageView>(R.id.batteryDown)}

        charging.setVisibility(View.INVISIBLE)
        battery.setVisibility(View.INVISIBLE)

        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            applicationContext.registerReceiver(null, ifilter)
        }

        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        if(isCharging) {
            Toast.makeText(applicationContext, "Charging", Toast.LENGTH_LONG).show()
            charging.setVisibility(View.VISIBLE)
            battery.setVisibility(View.INVISIBLE)
        } else {
            Toast.makeText(applicationContext,"Not Charging", Toast.LENGTH_LONG).show()
            charging.setVisibility(View.INVISIBLE)
            battery.setVisibility(View.VISIBLE)
        }
    }
}

class PowerBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("BATDEV", p0.toString())
        Log.d("BATDEV", p1.toString())
        Log.d("BATDEV", "yousk2")
    }

}