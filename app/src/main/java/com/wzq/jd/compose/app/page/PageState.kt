package com.wzq.jd.compose.app.page

/**
 * create by wzq on 2023/12/5
 *
 */
sealed class PageState {
    data object None : PageState()
    data object Loading : PageState()
    class Success(private val data: Any?) : PageState() {
        @Suppress("UNCHECKED_CAST")
        fun <T> get() = data as? T
    }

    class Failure(val exception: Throwable? = null, val tag: Any? = null) : PageState()
}