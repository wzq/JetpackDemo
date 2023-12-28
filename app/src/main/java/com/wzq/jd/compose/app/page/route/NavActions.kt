package com.wzq.jd.compose.app.page.route

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.wzq.jd.compose.app.data.model.Categories

interface CommonActions {
    fun goBack(): Boolean
}

class NavActions(private val navController: NavController) : CommonActions {
    fun toWebScreen(url: String?) {
        navController.navigate(ScreenPath.Web.createPath(url))
    }

    fun toSearch() {
        navController.navigate(ScreenPath.Search.route)
    }

    fun toCategory(position: Int = 0, categories: Categories) {
        val path = ScreenPath.Category.createPath(position)
        navController.graph.findNode(path)?.id?.also { id ->
            navController.navigate(
                id,
                args = bundleOf("data" to categories, "position" to position)
            )
        }

    }

    fun toSetting() {
        navController.navigate(ScreenPath.Setting.route)
    }

    override fun goBack() = navController.navigateUp()
}