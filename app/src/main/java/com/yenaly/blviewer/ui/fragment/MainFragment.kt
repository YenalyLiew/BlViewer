package com.yenaly.blviewer.ui.fragment

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.FragmentMainBinding
import com.yenaly.blviewer.ui.adapter.PartitionVpAdapter
import com.yenaly.blviewer.ui.viewmodel.MainViewModel
import com.yenaly.yenaly_libs.base.YenalyFragment

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 16:15
 * @Description : Description...
 */
class MainFragment : YenalyFragment<FragmentMainBinding, MainViewModel>() {

    private val tabList = listOf(
        R.string.aidol,
        R.string.magazine,
        R.string.thailand_sexy,
        R.string.chinese_sexy,
        R.string.american,
        R.string.japan,
        R.string.china,
        R.string.taiwan,
        R.string.thailand,
        R.string.korea
    )

    override fun initData(savedInstanceState: Bundle?) {
        binding.mfViewPager.adapter = PartitionVpAdapter(this)
        TabLayoutMediator(binding.mfTabLayout, binding.mfViewPager) { tab, position ->
            tab.setText(tabList[position])
        }.attach()
    }
}