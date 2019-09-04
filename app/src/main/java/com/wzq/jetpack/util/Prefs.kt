package com.wzq.jetpack.util

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.wzq.jetpack.App
import timber.log.Timber


/**
 * Created by wzq on 2019-07-18
 *
 */
object Prefs {

    const val USER_ID = "user_id"
    const val USER_NAME = "user_name"
    const val USER_INFO = "user_info"
    const val SEARCH_HISTORY = "SEARCH_HISTORY"

    private val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.context)

    @Suppress("UNCHECKED_CAST")
    fun <T> get(name: String, default: T): T {
        with(preference) {
            val res: Any = when (default) {
                is Long -> getLong(name, default)
                is String -> getString(name, default)
                is Int -> getInt(name, default)
                is Boolean -> getBoolean(name, default)
                is Float -> getFloat(name, default)
                else -> throw IllegalArgumentException("This type of data cannot be saved!")
            }
            return res as T
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun <T> set(name: String, value: T) = with(preference.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }.apply()
    }


    fun appendString(name:String, value: String, repeatable: Boolean = true) {
        val rv = if (repeatable) {
            get(name, "").plus(value)
        } else {
            get(name, "").replace(value, "").plus(value)
        }
        Timber.i(rv)
        set(name, rv)
    }
}