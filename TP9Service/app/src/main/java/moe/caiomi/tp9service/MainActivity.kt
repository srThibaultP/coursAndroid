package moe.caiomi.tp9service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if(notificationManager.getNotificationChannel(applicationContext.getString(R.string.app_name)) == null) {
                val defaultChannel = NotificationChannel(
                    applicationContext.getString(R.string.app_name),
                    applicationContext.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH
                    ).apply {
                        description = ""
                        enableLights(true)
                        lightColor = Color.WHITE
                        enableVibration(false)
                    }
                notificationManager.createNotificationChannel(defaultChannel)
            }
        }

        foregroundNotificationBuilder(applicationContext).setContentText("Service en cours d'exécution...").build()
    }
}

class FormationService : Service(){
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}

private fun foregroundNotificationBuilder(context: Context): NotificationCompat.Builder {
    return NotificationCompat.Builder(context, context.getString(R.string.app_name))
        .setContentText(context.getString(R.string.app_name))
        .setOngoing(true)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setColor(ResourcesCompat.getColor(context.resources, R.color.colorprimary, context.theme))
        .setCategory(NotificationCompat.CATEGORY_SERVICE)
}