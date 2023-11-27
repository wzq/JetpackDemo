package com.wzq.jd.compose.app.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

/**
 * create by wzq on 2023/11/27
 *
 */
@Composable
fun HomePage() {
    val data = remember {
        mutableStateListOf<String>().apply {
            repeat(20) {
                add("i$it")
            }
        }
    }
    LazyColumn(content = { 
        items(data) {
            Text(text = "item -> $it")
        }
    })
}