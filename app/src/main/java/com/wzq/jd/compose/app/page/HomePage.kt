package com.wzq.jd.compose.app.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.wzq.jd.compose.app.data.ArticleItem
import com.wzq.jd.compose.app.data.RemoteDataRepo

/**
 * create by wzq on 2023/11/27
 *
 */
@Composable
fun HomePage() {
    val data = remember {
        mutableStateListOf<ArticleItem>()
    }
    LaunchedEffect(key1 = true, block = {
        RemoteDataRepo.getHomeArticleList().onSuccess {
            data.addAll(it.data.listData)
        }
    })

    LazyColumn(content = {
        items(data) {
            ArticleItemPage(itemData = it)
        }
    })

}
