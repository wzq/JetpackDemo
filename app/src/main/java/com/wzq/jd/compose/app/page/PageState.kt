package com.wzq.jd.compose.app.page

/**
 * create by wzq on 2023/12/5
 *
 */
sealed class PageState<out T> {
    data object None : PageState<Nothing>()
    data object Loading : PageState<Nothing>()
    class Success<out T>(val data: T) : PageState<T>()
    class Failure(val exception: Throwable? = null, val tag: Any? = null) : PageState<Nothing>()
}