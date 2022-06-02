package com.yenaly.blviewer.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.yenaly.blviewer.logic.NetworkRepo
import com.yenaly.yenaly_libs.base.YenalyViewModel

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/30 030 16:14
 * @Description : Description...
 */
class PicDetailViewModel(application: Application) : YenalyViewModel(application) {
    private val _albumLiveData = MutableLiveData<String>()
    val albumLiveData = _albumLiveData.switchMap {
        NetworkRepo.loadAlbumWebFromBL(it).asLiveData()
    }

    fun loadAlbumWebFromBL(legCode: String) {
        _albumLiveData.value = legCode
    }
}