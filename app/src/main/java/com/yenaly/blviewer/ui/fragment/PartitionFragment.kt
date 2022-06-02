package com.yenaly.blviewer.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yenaly.blviewer.databinding.FragmentPartitionBinding
import com.yenaly.blviewer.logic.*
import com.yenaly.blviewer.ui.adapter.PicInfoRvAdapter
import com.yenaly.blviewer.ui.viewmodel.MainViewModel
import com.yenaly.yenaly_libs.base.YenalyFragment
import com.yenaly.yenaly_libs.utils.arguments
import kotlinx.coroutines.launch

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 18:24
 * @Description : Description...
 */
class PartitionFragment : YenalyFragment<FragmentPartitionBinding, MainViewModel>() {

    private val partition by arguments(TO_PARTITION, AIDOL)
    private val partitionPicRvAdapter by lazy { PicInfoRvAdapter(this) }

    @SuppressLint("SetTextI18n")
    override fun initData(savedInstanceState: Bundle?) {

        binding.pfRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.pfRecyclerView.adapter = partitionPicRvAdapter

        binding.pfSwipeRefresh.setOnRefreshListener {
            switchPartition(partition)
        }

        switchPartition(partition)

        lifecycleScope.launch {
            partitionPicRvAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && partitionPicRvAdapter.itemCount == 0
                binding.pfRecyclerView.isGone =
                    isListEmpty || loadState.source.refresh is LoadState.Error
                binding.pfSwipeRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading
                binding.pfErrorTip.isVisible = loadState.source.refresh is LoadState.Error
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.source.refresh as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Log.d("error_tip", it.error.message.toString())
                    binding.pfErrorTip.text = "🥺 ${it.error.message}"
                }
            }
        }
    }

    private fun switchPartition(partition: Int) {
        when (partition) {
            AIDOL -> getPartitionInfoFromBL(Partition.AIDOL.webName)
            MAGAZINE -> getPartitionInfoFromBL(Partition.MAGAZINE.webName)
            THAILAND_SEXY -> getPartitionInfoFromBL(Partition.THAILAND_SEXY.webName)
            CHINESE_SEXY -> getPartitionInfoFromBL(Partition.CHINESE_SEXY.webName)
            AMERICAN -> getPartitionInfoFromBL(Partition.AMERICAN.webName)
            JAPAN -> getPartitionInfoFromBL(Partition.JAPAN.webName)
            CHINA -> getPartitionInfoFromBL(Partition.CHINA.webName)
            TAIWAN -> getPartitionInfoFromBL(Partition.TAIWAN.webName)
            THAILAND -> getPartitionInfoFromBL(Partition.THAILAND.webName)
            KOREA -> getPartitionInfoFromBL(Partition.KOREA.webName)
            else -> throw IllegalArgumentException("this partition does not exist.")
        }
    }

    private fun getPartitionInfoFromBL(partition: String) {
        lifecycleScope.launch {
            viewModel.getPartitionInfoFromBL(partition).collect(partitionPicRvAdapter::submitData)
        }
    }
}