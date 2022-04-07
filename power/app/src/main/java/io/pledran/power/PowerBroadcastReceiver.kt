package io.pledran.power

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PowerBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Intent.ACTION_POWER_CONNECTED, Intent.ACTION_POWER_DISCONNECTED -> context.sendBroadcast(intent)
        }
    }
}