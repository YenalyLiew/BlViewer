package com.yenaly.blviewer

import com.google.android.material.color.DynamicColors
import com.yenaly.yenaly_libs.base.YenalyApplication

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/06/04 004 23:08
 * @Description : Description...
 */
class BlApplication : YenalyApplication() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}