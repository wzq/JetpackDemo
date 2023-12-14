package com.wzq.jd.compose.app.page.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.SearchHotWords
import com.wzq.jd.compose.app.page.CommonActions
import com.wzq.jd.compose.app.page.PageState
import com.wzq.jd.compose.app.page.home.ArticleItemPage

/**
 * create by wzq on 2023/11/24
 *
 */
@Composable
fun SearchScreen(
    navActions: CommonActions,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    )
            ) {
                SearchPageBar(viewModel) {
                    navActions.goBack()
                }
            }
        },
    ) { pv ->
        Box(
            modifier = Modifier
                .padding(pv)
                .fillMaxSize()
        ) {
            when (val state = viewModel.pageState.value) {
                PageState.None -> {
                    DefaultPage(hotWords = viewModel.hotWords) {
                        viewModel.searchResult(it)
                    }
                }

                PageState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is PageState.Success -> {
                    val list: List<ArticleItem> = state.get() ?: emptyList()
                    LazyColumn(content = {
                        items(list, key = { it.id }) {
                            ArticleItemPage(itemData = it) {}
                        }
                    })
                }

                else -> {}
            }

        }

    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun DefaultPage(hotWords: List<SearchHotWords>, onItemClick: (String) -> Unit) {
    val selectRecord = remember {
        mutableStateMapOf<Int, Boolean>()
    }
    FlowRow(
        Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        hotWords.forEachIndexed { index, hotWords ->
            ElevatedFilterChip(selected = selectRecord[index] ?: false, onClick = {
                selectRecord[index] = !(selectRecord[index] ?: false)
                onItemClick(hotWords.name)
            }, leadingIcon = {
                if (selectRecord[index] == true) {
                    Icon(Icons.Default.Check, contentDescription = null)
                }
            }, label = {
                Text(text = hotWords.name)
            })
        }
    }
}