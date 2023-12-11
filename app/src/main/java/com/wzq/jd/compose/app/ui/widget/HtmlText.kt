package com.wzq.jd.compose.app.ui.widget

import android.util.TypedValue
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

/**
 * create by wzq on 2023/12/8
 *
 */
@Composable
fun HtmlText(modifier: Modifier, color: Int, size: Float, text: String) {
    AndroidView(
        factory = {
            TextView(it).apply {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
                setTextColor(color)

            }
        },
        modifier = modifier
    ) {
        it.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

}