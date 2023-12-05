package com.wzq.jd.compose.app.page.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * create by wzq on 2023/11/24
 *
 */
@Composable
fun SearchPage(navController: NavController) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    )
            ) {
                SearchBar {
                    navController.navigateUp()
                }
            }
        },
    ) { pv ->
        Box(modifier = Modifier.padding(pv))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBar(onBackPressed: () -> Unit) {
    val searchWords = remember {
        mutableStateOf("")
    }
    val isActive = remember {
        mutableStateOf(false)
    }
    SearchBar(query = searchWords.value, onQueryChange = {
        searchWords.value = it
        isActive.value = it.isNotEmpty()
    }, onSearch = {}, active = isActive.value, onActiveChange = {}, leadingIcon = {
        IconButton(onClick = {
            if (isActive.value) {
                isActive.value = false
            } else {
                onBackPressed()
            }
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
    }, modifier = Modifier
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxWidth(),
    ) {
        // TODO: search history
    }
}