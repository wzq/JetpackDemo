package com.wzq.jd.compose.app.page

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wzq.jd.compose.app.WebActivity
import com.wzq.jd.compose.app.data.ArticleItem

/**
 * create by wzq on 2023/11/27
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItemPage(itemData: ArticleItem) {
    val context = LocalContext.current
    Card(
        onClick = {
            WebActivity.open(context, itemData.link)
        }, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = itemData.title,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Row(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Text(
                text = itemData.chapterName,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = itemData.niceShareDate,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}