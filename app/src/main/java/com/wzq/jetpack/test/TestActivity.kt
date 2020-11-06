package com.wzq.jetpack.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.startup.AppInitializer
import com.wzq.jetpack.OtherInitializer
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ActivityTestBinding
import com.wzq.jetpack.util.openPage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 * create by wzq on 2020/11/4
 *
 */
class TestActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataStore = AppInitializer.getInstance(this).initializeComponent(OtherInitializer::class.java)
        val userId = preferencesKey<Int>("user_id")
        lifecycleScope.launchWhenCreated {
            dataStore.data.map {
                it[userId]
            }.collect {
                println(it)
            }
        }

    }

    override fun onClick(v: View?) {
        v ?: return
        val context = v.context
        when (v.id) {
            R.id.b1 ->{
                openPage(ImmersiveActivity::class)
            }
        }
    }


}