package com.wzq.jetpack.ui.activity

import android.os.Bundle
import com.wzq.jetpack.R

class QuestionActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.drawer_question)
        setContentView(R.layout.activity_question)

    }
}