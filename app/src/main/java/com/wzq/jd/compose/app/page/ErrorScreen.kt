package com.wzq.jd.compose.app.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * create by wzq on 2023/12/14
 *
 */
@Composable
fun ErrorScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}