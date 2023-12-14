package com.wzq.jd.compose.app.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wzq.jd.compose.app.data.model.Categories

/**
 * create by wzq on 2023/12/1
 *
 */
@Composable
fun HomeCategoryPage(
    categories: List<Categories>,
    onItemClick: (Categories, Int) -> Unit
) {
    if (categories.isEmpty()) {
        return
    }
    LazyColumn(content = {
        items(categories, key = { it.id }) {
            CategoryItem(it) { position ->
                onItemClick(it, position)
            }
        }
    })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryItem(category: Categories, onItemClick: (Int) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DividerDefaults.color)
                .padding(8.dp),
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            category.children.forEachIndexed { index, item ->
                AssistChip(onClick = { onItemClick(index) }, label = { Text(text = item.name) })
            }
        }
    }

}