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
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wzq.jd.compose.app.data.model.SearchHotWords
import com.wzq.jd.compose.app.page.main.ArticleItemPage

/**
 * create by wzq on 2023/11/24
 *
 */
@Composable
fun SearchPage(
    navController: NavController,
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
                    navController.navigateUp()
                }
            }
        },
    ) { pv ->
        Box(modifier = Modifier.padding(pv)) {
            if (viewModel.result.isEmpty() || viewModel.keywords.value.isEmpty()) {
                DefaultPage(hotWords = viewModel.hotWords) {
                    viewModel.searchResult(it)
                }
            } else {
                LazyColumn(content = {
                    items(viewModel.result, key = { it.id }) {
                        ArticleItemPage(itemData = it) {}
                    }
                })
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