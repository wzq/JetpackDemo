@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.wzq.jd.compose.app.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
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
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/11/24
 *
 */
@Composable
fun MainPage(navController: NavHostController) {
    val pagerState = rememberPagerState { 3 }
    Scaffold(
        topBar = {
            MainTopBar(navController)
        },
        bottomBar = {
            MainBottomBar(pagerState)
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            when (it) {
                0 -> HomePage()
                else -> {
                    Text(text = "page at : $it")
                }
            }
        }
    }
}

@Composable
fun MainBottomBar(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
        IconButton(modifier = Modifier.weight(1f), onClick = {
            coroutineScope.launch {
                pagerState.scrollToPage(0)
            }
        }) {
            Icon(Icons.Default.Home, null)
        }
        IconButton(modifier = Modifier.weight(1f), onClick = {
            coroutineScope.launch {
                pagerState.scrollToPage(1)
            }
        }) {
            Icon(Icons.Default.ShoppingCart, null)
        }
        IconButton(modifier = Modifier.weight(1f), onClick = {
            coroutineScope.launch {
                pagerState.scrollToPage(2)
            }
        }) {
            Icon(Icons.Default.AccountCircle, null)
        }
    }
}

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