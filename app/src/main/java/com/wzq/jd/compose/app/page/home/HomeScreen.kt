package com.wzq.jd.compose.app.page.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.wzq.jd.compose.app.page.NavActions

/**
 * create by wzq on 2023/11/24
 *
 */
@Composable
fun HomeScreen(viewModel: HomeViewModel, navActions: NavActions) {
    val pagerState = remember { mutableIntStateOf(0) }

    Scaffold(topBar = {
        HomeTopBar {
            navActions.toSearch()
        }
    }, bottomBar = {
        HomeBottomBar { index ->
            pagerState.intValue = index
        }
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (pagerState.intValue) {
                0 -> HomeIndexPage(viewModel.homeList) {
                    navActions.toWebScreen(it)
                }

                1 -> HomeProjectPage(projectList = viewModel.projectList) {
                    navActions.toWebScreen(it)
                }

                2 -> HomeCategoryPage(categories = viewModel.categories) { item, childIndex ->
                    navActions.toCategory(childIndex, item)
                }
            }
        }
    }
}

@Composable
fun HomeBottomBar(onItemClick: (Int) -> Unit) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
        arrayOf(
            Icons.Default.Home, Icons.Default.ShoppingCart, Icons.Default.AccountCircle
        ).forEachIndexed { index, icon ->
            IconButton(modifier = Modifier.weight(1f), onClick = { onItemClick(index) }) {
                Icon(icon, null)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navigationToSearch: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "App")
        },
        actions = {
            IconButton(onClick = navigationToSearch) {
                Icon(Icons.Default.Search, null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}