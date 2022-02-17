package com.wzq.sample.experiment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzq.sample.R

class T2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        println(callingActivity?.shortClassName)
    }
}