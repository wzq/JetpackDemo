package com.wzq.jd.compose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wzq.jd.compose.app.data.model.Categories
import com.wzq.jd.compose.app.page.PageRouter
import com.wzq.jd.compose.app.page.WebPage
import com.wzq.jd.compose.app.page.main.CategoriesDetailPage
import com.wzq.jd.compose.app.page.main.MainPage
import com.wzq.jd.compose.app.page.search.SearchPage
import com.wzq.jd.compose.app.ui.theme.JetpackDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JetpackDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("JetpackDemo")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "main",
        route = name,
        enterTransition = {
            slideInHorizontally { it }
        },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { slideOutHorizontally { it } }) {
        composable(PageRouter.Main.name) { MainPage(navController) }
        composable(PageRouter.Search.name) { SearchPage(navController = navController) }
        composable(PageRouter.Web.name, PageRouter.Web.args) {
            WebPage(navController, it.arguments?.getString("url"))
        }
        composable(PageRouter.Categories.name) {
            CategoriesDetailPage(
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackDemoTheme {
        Greeting("Android")
    }
}