@file:Suppress("unused")

package com.yenaly.yenaly_libs.utils

/**
 * 将文字复制到剪切板
 *
 * @param label   为此文字设置的用户可见的标签 (optional)
 */
fun CharSequence?.copyToClipboard(label: CharSequence? = null) =
    ClipboardUtil.copyTextToClipboard(applicationContext, label, this)

/**
 * 剪贴板中最近一次的内容
 */
inline val textFromClipBoard get() = ClipboardUtil.getTextFromClipboard(applicationContext)

/**
 * 清除剪切板内容
 */
fun clearClipBoard() = ClipboardUtil.clearClipBoard(applicationContext)