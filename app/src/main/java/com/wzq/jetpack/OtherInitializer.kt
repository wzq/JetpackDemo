package com.wzq.jetpack

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.startup.Initializer
import timber.log.Timber

/**
 * create by wzq on 2020/11/5
 *
 */
// Initializes.
class OtherInitializer : Initializer<DataStore<Preferences>> {

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(BaseInitializer::class.java)
    }

    override fun create(context: Context): DataStore<Preferences> {
        Timber.d("other init")
        val defaultName = context.packageName + "_preferences"
        return context.createDataStore(
            BuildConfig.APPLICATION_ID, migrations = listOf(
                SharedPreferencesMigration(context, defaultName)
            )
        )
    }

}