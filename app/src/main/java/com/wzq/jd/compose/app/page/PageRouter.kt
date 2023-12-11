package com.wzq.jd.compose.app.page

import androidx.core.os.bundleOf
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * create by wzq on 2023/11/27
 *
 */
sealed class PageRouter(val name: String, val args: List<NamedNavArgument> = emptyList()) {
    data object Main : PageRouter("main")

    data object Web :
        PageRouter("web?url={url}", listOf(navArgument("url") { defaultValue = "" })) {
        fun navigation(navController: NavController, url: String) {
            navController.navigate("web?url=${url}")
        }
    }

    data object Search : PageRouter("search")

    data object Categories :
        PageRouter("categories") {
        fun navigation(id: Int) = "categories/${id}"
    }
}

fun NavController.pageNavigate(name: String, vararg params: Pair<String, Any?>) {
    graph.findNode(name)?.id?.also {
        navigate(it, bundleOf(*params))
    }
}
