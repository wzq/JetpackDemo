package com.wzq.jd.compose.app.page.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wzq.jd.compose.app.page.ErrorScreen
import com.wzq.jd.compose.app.page.NavActions
import com.wzq.jd.compose.app.page.home.ArticleItemPage
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/11
 *
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(
    navActions: NavActions, viewModel: CategoriesViewModel
) {
    val categories = viewModel.categories
    val dataList = categories?.children
    if (dataList.isNullOrEmpty()) {
        ErrorScreen()
        return
    }

    val pagerState = rememberPagerState(initialPage = viewModel.initPosition) { dataList.size }
    val currentSelectedIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState, block = {
        snapshotFlow { pagerState.currentPage }.collect {
            viewModel.getItemList(pagerState.currentPage)
        }
    })
    ;
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = categories.name, maxLines = 1)
            },
            navigationIcon = {
                IconButton(onClick = { navActions.goBack() }) {
                    Icon(Icons.Default.ArrowBack, null)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
        ScrollableTabRow(
            selectedTabIndex = currentSelectedIndex.value,
            edgePadding = 0.dp,
        ) {
            dataList.forEachIndexed { index, knowledgeCategories ->
                Tab(selected = currentSelectedIndex.value == index, onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                }) {
                    Text(text = knowledgeCategories.name, modifier = Modifier.padding(8.dp))
                }
            }

        }
        Divider(thickness = 8.dp, color = Color.Transparent)
        HorizontalPager(
            state = pagerState, beyondBoundsPageCount = 3
        ) { page ->
            val articleList = viewModel.pagerData[page]
            if (articleList.isNullOrEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize(), content = {
                    items(articleList) {
                        ArticleItemPage(itemData = it) { url -> navActions.toWebScreen(url) }
                    }
                })
            }
        }

    }
}