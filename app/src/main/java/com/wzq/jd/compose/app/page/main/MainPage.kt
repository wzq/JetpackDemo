package com.wzq.jd.compose.app.page.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/11/24
 *
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPage(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }

    Scaffold(topBar = {
        MainTopBar(navController)
    }, bottomBar = {
        MainBottomBar { index ->
            scope.launch {
                pagerState.scrollToPage(index)
            }
        }
    }) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background),
            beyondBoundsPageCount = 3
        ) { page ->
            when (page) {
                0 -> HomePage(navController, viewModel.homeList)
                1 -> ProjectPage(navController, viewModel.projectList)
                2 -> CategoriesPage()
                else -> {
                    throw Exception("Shouldn't Happen!")
                }
            }
        }
    }
}

@Composable
fun MainBottomBar(onItemClick: (Int) -> Unit) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
        arrayOf(
            Icons.Default.Home,
            Icons.Default.ShoppingCart,
            Icons.Default.AccountCircle
        ).forEachIndexed { index, icon ->
            IconButton(modifier = Modifier.weight(1f), onClick = { onItemClick(index) }) {
                Icon(icon, null)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "App")
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("search")
            }) {
                Icon(Icons.Default.Search, null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}