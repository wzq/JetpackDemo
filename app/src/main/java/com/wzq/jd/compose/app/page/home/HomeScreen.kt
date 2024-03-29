package com.wzq.jd.compose.app.page.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.wzq.jd.compose.app.page.route.NavActions
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/11/24
 *
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navActions: NavActions) {
    val pagerState = rememberPagerState(0) { 4 }
    val localScope = rememberCoroutineScope()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(topBar = {
        HomeTopBar(pagerState.currentPage, {
            navActions.toSearch()
        }) {
            navActions.toSetting()
        }
    }, bottomBar = {
        HomeBottomBar(pagerState.currentPage) {
            localScope.launch {
                pagerState.scrollToPage(it)
            }
        }
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { paddingValues ->
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            beyondBoundsPageCount = pagerState.pageCount,
            userScrollEnabled = false
        ) { currentPagerNum ->
            when (currentPagerNum) {
                0 -> HomeIndexPage(
                    pageData = viewModel.articleList,
                    onItemClick = { navActions.toWebScreen(it) },
                )

                1 -> HomeProjectPage(
                    pageData = viewModel.projectList,
                    navigateToWeb = { navActions.toWebScreen(it) })

                2 -> HomeCategoryPage(
                    categories = viewModel.categories,
                    onItemClick = { categories, i -> navActions.toCategory(i, categories) })

                3 -> Text(text = "TODO Profile")

                else -> throw Exception()
            }
        }
    }
}

@Composable
fun HomeBottomBar(selectedIndex: Int, onItemClick: (Int) -> Unit) {
    NavigationBar {
        arrayOf(
            Icons.Default.Home,
            Icons.Default.ShoppingCart,
            Icons.Default.List,
            Icons.Default.AccountCircle
        ).forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemClick(index) },
                icon = { Icon(imageVector = icon, contentDescription = null) },
                label = { Text(text = "label") },
                alwaysShowLabel = true
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    currentPager: Int,
    navigationToSearch: () -> Unit,
    navigationToSetting: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "App")
        },
        actions = {
            if (currentPager == 3) {
                IconButton(onClick = navigationToSetting) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
            } else {
                IconButton(onClick = navigationToSearch) {
                    Icon(Icons.Default.Search, null)
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}