package com.wzq.jd.compose.app.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wzq.jd.compose.app.WebActivity
import com.wzq.jd.compose.app.data.ArticleItem
import com.wzq.jd.compose.app.data.RemoteDataRepo

/**
 * create by wzq on 2023/12/1
 *
 */
@Composable
fun ProjectPage() {
    val data = remember {
        mutableStateListOf<ArticleItem>()
    }
    LaunchedEffect(key1 = true, block = {
        RemoteDataRepo.getProjectList().onSuccess {
            data.addAll(it.data.listData)
        }
    })

    val context = LocalContext.current
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(data) {
                ProjectItem(item = it) {
                    WebActivity.open(context, it.link)
                }
            }
        })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(item: ArticleItem, onItemClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = onItemClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.envelopePic,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
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
                    modifier = Modifier.padding(8.dp)
                )
            }

        }

    }
}