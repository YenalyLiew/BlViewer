@file:Suppress("unused")

package com.yenaly.yenaly_libs.utils

/**
 * 把一个数转化为带万、亿的格式，常用于视频播放量等。
 *
 * 例如：102002 -> 10.2万
 *
 * @author Yenaly Liew
 */
fun Long.toPlayCountCase(): String = TextUtil.toPlayCountCase(this)

/**
 * 把一个秒数转化为时间格式，常用于视频持续时间。
 *
 * 例如：123(s) -> 02:03
 *
 * @author Yenaly Liew
 */
fun Long.secondToTimeCase(): String = TextUtil.secondToTimeCase(this)

fun CharSequence.toPinyin(split: CharSequence = ""): String = PinyinUtil.ccs2Pinyin(this, split)