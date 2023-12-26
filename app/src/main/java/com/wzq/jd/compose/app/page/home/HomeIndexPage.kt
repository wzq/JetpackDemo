package com.wzq.jd.compose.app.page.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.page.PageState
import com.wzq.jd.compose.app.ui.widget.HtmlText
import io.ktor.http.encodeURLPath
import kotlinx.coroutines.flow.Flow

/**
 * create by wzq on 2023/11/27
 *
 */
@Composable
fun HomeIndexPage(
    state: PageState<List<ArticleItem>>,
    onItemClick: (String) -> Unit,
    pageData: Flow<PagingData<ArticleItem>>
) {
    val lazyPagingItems = pageData.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyPagingItems.itemCount, lazyPagingItems.itemKey()) {
            ArticleItemPage(itemData = lazyPagingItems[it]!!, onItemClick)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItemPage(itemData: ArticleItem, onItemClick: (String) -> Unit) {
    Card(
        onClick = {
            onItemClick(itemData.link.encodeURLPath())
        }, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        HtmlText(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp
            ),
            color = MaterialTheme.colorScheme.onSurface.toArgb(),
            size = 16f,
            text = itemData.title
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
