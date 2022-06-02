@file:Suppress("unused")

package com.yenaly.yenaly_libs.utils

/**
 * 获取本地APP版本号，获取失败则返回null
 *
 * @return 版本号，例如 1.0.0
 */
inline val appLocalVersion: String? get() = AppUtil.getAppLocalVersion(applicationContext)

/**
 * 获取本地APP版本代码，获取失败则返回0
 *
 * @return 版本代码，例如 12314355
 */
inline val appLocalVersionCode: Long get() = AppUtil.getAppLocalVersionCode(applicationContext)

/**
 * 获取当前APP的包名
 */
inline val packageName: String get() = applicationContext.packageName

/**
 * 获得App可及屏幕宽度
 */
val appScreenWidth: Int
    get() {
        val value = DeviceUtil.getAppScreenSize(applicationContext)
        return value.first
    }

/**
 * 获得App可及屏幕高度
 */
val appScreenHeight: Int
    get() {
        val value = DeviceUtil.getAppScreenSize(applicationContext)
        return value.second
    }