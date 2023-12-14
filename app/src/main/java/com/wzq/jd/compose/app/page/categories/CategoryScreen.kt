package com.wzq.jd.compose.app.page.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wzq.jd.compose.app.data.model.Categories
import com.wzq.jd.compose.app.page.NavActions
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/11
 *
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(
    navActions: NavActions,
    categories: Categories?,
    viewModel: CategoriesViewModel = viewModel()
) {
    val dataList = categories?.children
    if (dataList.isNullOrEmpty()) {
        return
    }

    val pagerState = rememberPagerState(initialPage = viewModel.initPosition) { dataList.size }
    val currentSelectedIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }
    val scope = rememberCoroutineScope()

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
            divider = {}) {
            dataList.forEachIndexed { index, knowledgeCategories ->
                Tab(
                    selected = currentSelectedIndex.value == index,
                    onClick = {
                        scope.launch { pagerState.scrollToPage(index) }
                    }) {
                    Text(text = knowledgeCategories.name, modifier = Modifier.padding(8.dp))
                }
            }

        }
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 3
        ) { page ->
            Text(text = "item - >$page")
        }

    }
}