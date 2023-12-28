package com.wzq.jd.compose.app.page.route

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * create by wzq on 2023/11/27
 *
 */
sealed class ScreenPath(
    val route: String, val namedNavArguments: List<NamedNavArgument> = emptyList()
) {

    data object Home : ScreenPath("home")

    data object Web : ScreenPath("web?url={url}") {
        fun createPath(url: String?) = "web?url=${url}"
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

        fun createPath(position: Int) = "category?position=${position}"
    }

    data object Setting: ScreenPath("setting")
}





