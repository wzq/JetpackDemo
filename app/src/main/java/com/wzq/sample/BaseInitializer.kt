package com.wzq.sample

import android.content.Context
import androidx.startup.Initializer
import com.wzq.sample.service.WorkInitializer

/**
 * create by wzq on 2020/11/5
 *
 */
// Initializes App.
class BaseInitializer : Initializer<Unit> {

    override fun create(context: Context) {
//        context.dataStore.data.onEach {
//            println(it[stringPreferencesKey(Prefs.USER_NAME)])
//        }.launchIn(MainScope())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(WorkInitializer::class.java)
    }
}

//create dataStore instance
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "test_prefs",
//    produceMigrations = {
//        listOf(SharedPreferencesMigration(it, it.packageName + "_preferences"))
//    })
