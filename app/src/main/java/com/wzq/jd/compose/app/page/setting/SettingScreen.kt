package com.wzq.jd.compose.app.page.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.annotation.ExperimentalCoilApi
import com.wzq.jd.compose.app.AppContainer
import com.wzq.jd.compose.app.page.route.NavActions
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/28
 *
 */
@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navActions: NavActions) {
    val imageLoader = Coil.imageLoader(LocalContext.current)

    fun currentMemory() = (imageLoader.memoryCache?.size ?: 0) / 1024f / 1024f

    val memoryCacheSize = remember {
        mutableFloatStateOf(currentMemory())
    }
    val diskCacheSize: Float = (imageLoader.diskCache?.size ?: 0) / 1024f / 1024f

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val articleSize = remember {
        mutableIntStateOf(0)
    }
    val categoriesSize = remember {
        mutableIntStateOf(0)
    }
    val articleDao = AppContainer.database.articleDao()
    val categoriesDao = AppContainer.database.categoriesDao()
    LaunchedEffect(articleDao, categoriesDao, block = {
        articleSize.intValue = articleDao.getArticlesSize()
        categoriesSize.intValue = categoriesDao.getCategoriesSize()
    })

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "设置", maxLines = 1)
                },
                navigationIcon = {
                    IconButton(onClick = { navActions.goBack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(Modifier.padding(16.dp)) {
                TextButton(onClick = {
                    imageLoader.memoryCache?.clear()
                    memoryCacheSize.floatValue = currentMemory()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "已清理缓存",
                        )
                    }
                }) {
                    Text(text = "内存缓存: ${memoryCacheSize.floatValue} M")
                }
                Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
                Text(text = "磁盘缓存: $diskCacheSize M")

                Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

                Text(text = "数据库条目数量: \narticle=${articleSize.intValue} | category=${categoriesSize.intValue}")
            }
        }
    }

}