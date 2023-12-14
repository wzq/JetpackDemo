package com.wzq.jd.compose.app.page

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wzq.jd.compose.app.data.model.Categories
import com.wzq.jd.compose.app.page.categories.CategoryScreen
import com.wzq.jd.compose.app.page.home.HomeScreen
import com.wzq.jd.compose.app.page.search.SearchScreen
import com.wzq.jd.compose.app.page.web.WebScreen

/**
 * create by wzq on 2023/12/13
 *
 */
@Composable
fun MainNavGraph(hostName: String = "main", navController: NavHostController) {
    val startPage = "home"
    NavHost(navController = navController,
        startDestination = startPage,
        route = hostName,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { slideOutHorizontally { it } }) {

        val navActions = NavActions(navController)
        homePage(navActions)
        webPage(navActions)
        searchPage(navActions)
        categoryPage(navActions)
    }
}

private fun NavGraphBuilder.homePage(navActions: NavActions) = composable(ScreenPath.Home.route) {
    HomeScreen(viewModel(), navActions = navActions)
}

private fun NavGraphBuilder.webPage(navActions: NavActions) = composable(ScreenPath.Web.route) {
    WebScreen(navActions, it.arguments?.getString("url"))
}

private fun NavGraphBuilder.searchPage(navActions: NavActions) =
    composable(ScreenPath.Search.route) {
        SearchScreen(navActions)
    }

private fun NavGraphBuilder.categoryPage(navActions: NavActions) =
    composable(ScreenPath.Category.route, ScreenPath.Category.namedNavArguments) { backStackEntry ->
        val categories: Categories? = backStackEntry.arguments?.getParcelable("data")
        CategoryScreen(navActions = navActions, categories)
    }
