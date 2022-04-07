package io.pledran.countdown

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {

    private val button by lazy { findViewById<Button>(R.id.button) }
    private val textView by lazy { findViewById<TextView>(R.id.textView) }

    lateinit var countdown: AtomicInteger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startCountDownWithThread()
        }
    }

    private fun startCountDownWithThread() {
        button.isEnabled = false
        countdown = AtomicInteger(9)
        Thread {
            while (countdown.get() >= 0) {
                runOnUiThread {
                    textView.text = countdown.getAndDecrement().toString()
                }
                Thread.sleep(1_000)
            }
            runOnUiThread {
                button.isEnabled = true
            }
        }.start()
    }

    private fun startCountDownTimer() {
        object : CountDownTimer(10_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                textView.text = ""
            }

        }.start()
    }
}