package com.wzq.sample.experiment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.wzq.sample.R
import kotlinx.coroutines.flow.*

/**
 * Test for flow stateflow
 */
class T1Activity : AppCompatActivity() {
    val vm: T1ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_splash
        )


        vm.data.onEach {
            if (it.size == 15) {
                throw Exception("test exception")
            } else println(it)
        }
            .onCompletion {
                println("final $it")
            }
            .catch {
                println(it.message)
            }
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(lifecycleScope)

//        lifecycleScope.launch {wrerq
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                vm.data.collectLatest {
//                    println(it)
//                }
//            }
//        }

//        lifecycleScope.launchWhenStarted {
//            vm.data.collectLatest {
//                println(it)
//            }
//        }
//
//        var num = 10
//        val handler = Handler(Looper.getMainLooper()) {
//            vm.changeData(it.what)
//            num++
//            println(num)
//            it.target.sendEmptyMessageDelayed(num, 2000)
//            false
//        }.apply {
//            sendEmptyMessageDelayed(num, 2000)
//        }
        val preview = findViewById<ImageView>(R.id.preview)

        val contracts = ActivityResultContracts.TakePicturePreview()
        val getContent = registerForActivityResult(contracts) {
            preview.load(it)
        }
        findViewById<View>(R.id.btn).setOnClickListener {
            startActivityForResult(Intent(this, T2Activity::class.java), 111)
//            getContent.launch(null)
        }
    }
}


class T1ViewModel : ViewModel() {


    private val _data = MutableStateFlow(emptyList<String>())
    val data = _data.asStateFlow()


    fun changeData(num: Int) {
        _data.update { getFakeData(num) }
    }

    private fun getFakeData(num: Int): List<String> = buildList {
        for (i in 0..num) add("item $i")
    }
}