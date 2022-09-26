package com.wzq.sample.experiment

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.wzq.sample.R

class T2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        println(callingActivity?.shortClassName)

        val handler = Handler(Looper.getMainLooper()) {
            println("handler message")
            false
        }

        Message.obtain(handler).also {
            it.sendToTarget()
        }

        handler.looper.queue.addIdleHandler {
            println("idle")
            false
        }

//        handler.removeCallbacksAndMessages(null)
    }
}