package com.wzq.sample

import android.content.Context
import androidx.startup.Initializer

/**
 * create by wzq on 2020/11/5
 *
 */
// Initializes App.
class BaseInitializer : Initializer<Unit> {

    override fun create(context: Context) {

    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
