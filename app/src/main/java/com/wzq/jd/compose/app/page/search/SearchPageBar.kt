package com.wzq.jd.compose.app.page.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

/**
 * create by wzq on 2023/12/5
 *
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun SearchPageBar(
    viewModel: SearchViewModel, onBackPressed: () -> Unit,
) {
    val keyboardControl = LocalSoftwareKeyboardController.current
    val isActive = remember {
        mutableStateOf(false)
    }
    val paddingValues by remember {
        derivedStateOf { if (isActive.value) 0.dp else 16.dp }
    }
    SearchBar(
        query = viewModel.keywords.value,
        onQueryChange = {
            viewModel.setKeyWords(it)
            isActive.value = it.isNotEmpty()
        },
        onSearch = {
            viewModel.searchResult(it)
            isActive.value = false
        },
        active = isActive.value, onActiveChange = {},
        leadingIcon = {
            IconButton(onClick = {
                if (isActive.value) {
                    isActive.value = false
                } else {
                    onBackPressed()
                }
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        modifier = Modifier
            .padding(
                bottom = paddingValues / 2, start = paddingValues, end = paddingValues
            )
            .fillMaxWidth(),
    ) {
        LazyColumn(content = {
            items(viewModel.hotWords) {
                ClickableText(
                    text = AnnotatedString(it.name),
                    modifier = Modifier.padding(16.dp)
                ) { _ ->
                    viewModel.searchResult(it.name)
                    isActive.value = false
                    keyboardControl?.hide()
                }
            }
        })
    }
}