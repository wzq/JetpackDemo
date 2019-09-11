package com.wzq.jetpack.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ActivityLoginBinding
import com.wzq.jetpack.util.Preference
import com.wzq.jetpack.util.Prefs
import com.wzq.jetpack.viewmodel.LoginViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory
import org.greenrobot.eventbus.EventBus


/**
 * Created by wzq on 2019-07-16
 *
 */
class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    private var userId by Preference(Prefs.USER_ID, 0)
    private var userName by Preference(Prefs.USER_NAME, "")

    private val viewModel by viewModels<LoginViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "用户登录"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginSubmit.setOnClickListener {
            val a = binding.loginAccount.text.toString()
            val p = binding.loginPassword.text.toString()
            viewModel.login(a, p).observe(this, Observer {
                val id = it?.data?.id ?: 0
                if(id > 0) {
                    showDialog()
                    userId = id
                    userName = it?.data?.nickname ?: ""
                    EventBus.getDefault().post(it)
                }
            })
        }

    }


    private fun showDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("提示")
            .setMessage("登录成功")
            .setPositiveButton("确认") { _, _ ->
                finish()
            }
            .show()
    }

}