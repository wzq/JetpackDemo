package com.wzq.jetpack.util.thread

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext





class ScopeFactory(override val coroutineContext: CoroutineContext) : CoroutineScope


