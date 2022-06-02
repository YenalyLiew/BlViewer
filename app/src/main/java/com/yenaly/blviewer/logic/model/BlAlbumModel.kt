package com.yenaly.blviewer.logic.model

import java.io.Serializable

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/30 030 17:47
 * @Description : Description...
 */
data class BlAlbumModel(
    val legCode: String,
    val title: String,
    val tags: List<String>,
    val pic: List<Pic>
) : Serializable {
    data class Pic(
        val url: String,
        val desc: String?,
        val position: Int
    ) : Serializable
}