package com.wzq.sample.util

import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.wzq.sample.App

/**
 * create by wzq on 2023/6/21
 * 加密prefs
 */
object PrefsSafety {

    private val sharedPrefs by lazy {
        val context = App.context
        val mainKey =
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        EncryptedSharedPreferences.create(
            context,
            context.packageName.plus("_private_prefs"),
            mainKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    fun write(name: String, value: Any) {
        sharedPrefs.edit {
            when (value) {
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                is Int -> putInt(name, value)
                is Boolean -> putBoolean(name, value)
                is Float -> putFloat(name, value)
                else -> throw IllegalArgumentException("This type of data cannot be saved!")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> read(name: String, default: T): T = sharedPrefs.run {
        val value: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default) ?: default
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }
        value as T
    }

}