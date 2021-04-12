package com.wzq.sample.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wzq.sample.R
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.remote.Linker
import com.wzq.sample.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * create by wzq on 2021/4/6
 *
 */
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<View>(R.id.btn).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }


    fun testRoom(){
        lifecycleScope.launchWhenStarted {
            try {
                withContext(Dispatchers.Main.immediate) {
                    val roomDao = AppDatabase.getInstance().articleDao()
                    val s = Linker.mainApi.getArticles(1)
                    roomDao.insertAll(s.getOrThrow().datas)
                    delay(1000)
                   val data = roomDao.getArticles()
                    println(data.size)

                    println(data)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}