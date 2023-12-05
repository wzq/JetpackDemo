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
import androidx.navigation.navArgument
import com.wzq.jd.compose.app.page.WebPage
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
    NavHost(
        navController = navController,
        startDestination = "main",
        route = name,
        enterTransition = {
            slideInHorizontally { it }
        },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { slideOutHorizontally { it } }) {
        composable("main") { MainPage(navController) }
        composable("search") { SearchPage(navController = navController) }
        composable(
            "web?url={url}", arguments = listOf(navArgument("url") { defaultValue = "" })
        ) {
            WebPage(navController, it.arguments?.getString("url"))
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