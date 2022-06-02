@file:Suppress("unused")

package com.yenaly.yenaly_libs.utils

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

/**
 * 设置view的margins，设置相对位置margins更符合Google标准
 *
 * @param start  view的开始位
 * @param top    view的顶部
 * @param end    view的结束位
 * @param bottom view的底部
 */
fun View.setMargins(start: Int, top: Int, end: Int, bottom: Int) {
    if (this.layoutParams is MarginLayoutParams) {
        val p = this.layoutParams as MarginLayoutParams
        p.setMargins(0, top, 0, bottom)
        p.marginStart = start
        p.marginEnd = end
        this.requestLayout()
    }
}