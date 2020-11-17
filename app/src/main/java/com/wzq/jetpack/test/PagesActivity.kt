package com.wzq.jetpack.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.wzq.jetpack.R
import com.wzq.jetpack.test.video.VideoPage
import com.wzq.jetpack.test.video.VideoWithViewPager2Page

/**
 * create by wzq on 2020/11/9
 *
 */
class PagesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pages)

        val key = intent.getIntExtra("key", -1)
        supportFragmentManager.commit {
            replace(R.id.container, VideoWithViewPager2Page::class.java, null)
        }

    }
}