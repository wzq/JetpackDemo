package com.wzq.jd.compose.app.page

import androidx.core.os.bundleOf
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.wzq.jd.compose.app.data.model.Categories

/**
 * create by wzq on 2023/11/27
 *
 */
sealed class ScreenPath(
    val route: String, val namedNavArguments: List<NamedNavArgument> = emptyList()
) {

    data object Home : ScreenPath("home")

    data object Web : ScreenPath("web?url={url}") {
        fun getPath(url: String?) = "web?url=${url}"
    }

    data object Search : ScreenPath("search")

    data object Category :
        ScreenPath(
            "category?position={position}",
            listOf(
                navArgument("position") {
                    type = NavType.IntType
                },
            ),
        ) {

        fun getPath(position: Int) = "category?position=${position}"
    }
}

interface CommonActions {
    fun goBack(): Boolean
}

class NavActions(private val navController: NavController) : CommonActions {
    fun toWebScreen(url: String?) {
        navController.navigate(ScreenPath.Web.getPath(url))
    }

    fun toSearch() {
        navController.navigate(ScreenPath.Search.route)
    }

    fun toCategory(position: Int = 0, categories: Categories) {
        val path = ScreenPath.Category.getPath(position)
        navController.graph.findNode(path)?.id?.also { id ->
            navController.navigate(
                id,
                args = bundleOf("data" to categories, "position" to position)
            )
        }

    }

    override fun goBack() = navController.navigateUp()
}





