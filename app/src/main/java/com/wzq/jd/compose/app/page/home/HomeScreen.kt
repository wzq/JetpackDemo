package com.wzq.jd.compose.app.page.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
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
import com.wzq.jd.compose.app.page.NavActions
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/11/24
 *
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navActions: NavActions) {
    val pagerState = rememberPagerState(0) { 3 }
    val localScope = rememberCoroutineScope()

    Scaffold(topBar = {
        HomeTopBar {
            navActions.toSearch()
        }
    }, bottomBar = {
        HomeBottomBar { index ->
            localScope.launch {
                pagerState.scrollToPage(index)
            }
        }
    }) { paddingValues ->
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            beyondBoundsPageCount = pagerState.pageCount
        ) { currentPagerNum ->
            when (currentPagerNum) {
                0 -> HomeIndexPage(
                    articleList = viewModel.homeList,
                    onItemClick = { navActions.toWebScreen(it) })

                1 -> HomeProjectPage(
                    projectList = viewModel.projectList,
                    navigateToWeb = { navActions.toWebScreen(it) })

                2 -> HomeCategoryPage(
                    categories = viewModel.categories,
                    onItemClick = { categories, i -> navActions.toCategory(i, categories) })

                else -> throw Exception("todo")
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