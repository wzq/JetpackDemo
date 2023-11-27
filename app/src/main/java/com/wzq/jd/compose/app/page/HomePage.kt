package com.wzq.jd.compose.app.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.wzq.jd.compose.app.data.ArticleItem
import com.wzq.jd.compose.app.data.NetResult
import com.wzq.jd.compose.app.data.NetResultList
import com.wzq.jd.compose.app.data.NetworkUtil

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
        try {
            val response: NetResult<NetResultList<ArticleItem>> =
                NetworkUtil.requestGet(NetworkUtil.Url.HomeArticleList)
            data.addAll(response.data.listData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    })

    LazyColumn(content = {
        items(data) {
            ArticleItemPage(itemData = it)
        }
    })

}
