package moe.caiomi.tp7batterie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import moe.caiomi.tp7batterie.BuildConfig.DEBUG

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }
}

class PowerBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("BATDEV", p0.toString())
        Log.d("BATDEV", p1.toString())
        Log.d("BATDEV", "yousk2")
    }

}