package com.wzq.jd.compose.app.page.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.wzq.jd.compose.app.data.model.KnowledgeCategories

/**
 * create by wzq on 2023/12/1
 *
 */
@Composable
fun CategoriesPage(
    navController: NavHostController,
    categories: List<KnowledgeCategories>
) {
    if (categories.isEmpty()) {
        // TODO: not data
        return
    }
    LazyColumn(content = {
        items(categories, key = { it.id }) {
            CategoryItem(it) {
                navController.currentBackStackEntry?.savedStateHandle?.set("data", it)
                navController.navigate("categories")
            }
        }
    })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryItem(category: KnowledgeCategories, onItemClick: () -> Unit) {
    Column(
    ) {
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(DividerDefaults.color)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            category.children.forEach { item ->
                AssistChip(onClick = onItemClick, label = { Text(text = item.name) })
            }
        }
    }

}