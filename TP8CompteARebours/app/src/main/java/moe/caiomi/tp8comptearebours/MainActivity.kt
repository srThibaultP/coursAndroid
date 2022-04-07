package moe.caiomi.tp8comptearebours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start by lazy { findViewById<Button>(R.id.start) }
        val compteur by lazy { findViewById<TextView>(R.id.textView)}
        var i = 7
        compteur.text = i.toString()

        start.setOnClickListener(){
            val thread = Thread {
                i = 7
                while (i != -1) {
                    runOnUiThread {
                        compteur.text = i--.toString()
                    }
                    Thread.sleep(1000)
                }
            }
            thread.start()
        }
    }
}