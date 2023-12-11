package com.wzq.jd.compose.app.page.main

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.navDeepLink
import coil.compose.AsyncImage
import com.wzq.jd.compose.app.data.model.ArticleItem
import io.ktor.http.encodeURLPath

/**
 * create by wzq on 2023/12/1
 *
 */
@Composable
fun ProjectPage(navController: NavController, projectList: List<ArticleItem>) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(projectList) {
                ProjectItem(item = it) {
                    navController.navigate("web?url=${it.link.encodeURLPath()}")
                }
            }
        })

    navController.navigate("", )
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
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.padding(8.dp)
                )
            }

        }

    }
}