package com.wzq.jd.compose.app.page.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wzq.jd.compose.app.data.model.KnowledgeCategories

/**
 * create by wzq on 2023/12/11
 *
 */
@Composable
fun CategoriesDetailPage(navController: NavController, categories: List<KnowledgeCategories>?) {
    if (categories.isNullOrEmpty()) {
        return
    }
    var currentSelectedIndex by remember {
        mutableIntStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = currentSelectedIndex,
            edgePadding = 0.dp,
            divider = {}) {
            categories.forEachIndexed { index, knowledgeCategories ->
                Tab(
                    selected = currentSelectedIndex == index,
                    onClick = { currentSelectedIndex = index }) {
                    Text(text = knowledgeCategories.name, modifier = Modifier.padding(8.dp))
                }
            }

        }
    }
}