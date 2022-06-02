package com.yenaly.blviewer.logic.network

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 17:02
 * @Description : Description...
 */
object BlNetwork {
    const val BEAUTIFUL_LEG_BASE_URL = "https://www.beatifulleg.com/"
    const val BEAUTIFUL_LEG_CDN_URL = "https://cdn.beatifulleg.com/"

    val legNetwork = ServiceCreator.create<BlService>(BEAUTIFUL_LEG_BASE_URL)
    val legCdnNetwork = ServiceCreator.create<BlService>(BEAUTIFUL_LEG_CDN_URL)
}