package com.wzq.sample.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import com.wzq.sample.ui.detail.WebFragment
import com.wzq.sample.ui.main.MainFragment
import com.wzq.sample.ui.search.SearchFragment

/**
 *  navigation graph dsl
 */
object NavMap {
    private var counter = 1
    private fun autoId() = (counter++).toString()

    val id = autoId()

    object Dest {
        val main = autoId()
        val detail = autoId()
        val search = autoId()
    }

    //called when fragment attached
    fun newGraph(
        navController: NavController,
        startDestination: String,
        route: String? = null,
        customBuilder: (NavGraphBuilder.() -> Unit)? = null
    ) = with(navController) {
        graph = createGraph(startDestination, route) {
            fragment<MainFragment>(Dest.main)
            fragment<WebFragment>(Dest.detail)
            fragment<SearchFragment>(Dest.search)

            customBuilder?.invoke(this)
        }
    }
}