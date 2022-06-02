package com.yenaly.blviewer.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yenaly.blviewer.logic.Category
import com.yenaly.blviewer.logic.NetworkRepo
import com.yenaly.yenaly_libs.base.YenalyViewModel

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 10:38
 * @Description : Description...
 */
class MainViewModel(application: Application) : YenalyViewModel(application) {

    var lastSearchType: Category? = null
    var lastSearchKey: String? = null

    fun getPartitionInfoFromBL(partition: String) =
        NetworkRepo.getPartitionInfoFromBL(partition).cachedIn(viewModelScope)

    fun searchFromBL(category: Category, searchKey: String) =
        NetworkRepo.searchFromBL(category, searchKey).cachedIn(viewModelScope)
}