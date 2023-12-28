package com.wzq.jd.compose.app.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wzq.jd.compose.app.data.model.ArticleItem
import io.ktor.http.encodeURLPath
import kotlinx.coroutines.flow.Flow

/**
 * create by wzq on 2023/12/1
 *
 */
@Composable
fun HomeProjectPage(
    pageData: Flow<PagingData<ArticleItem>>,
    navigateToWeb: (String) -> Unit,
) {
    val data = pageData.collectAsLazyPagingItems()
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(data.itemCount, data.itemKey()) { position ->
                val item = data[position]
                if (item != null) {
                    ProjectItem(item = item) { navigateToWeb(item.link.encodeURLPath()) }
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(item: ArticleItem, onItemClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = onItemClick) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(item.envelopePic).crossfade(true).build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x81000000))
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = item.title,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}