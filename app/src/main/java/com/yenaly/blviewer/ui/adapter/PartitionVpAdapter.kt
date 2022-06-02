package com.yenaly.blviewer.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yenaly.blviewer.logic.*
import com.yenaly.blviewer.ui.fragment.PartitionFragment
import com.yenaly.yenaly_libs.utils.makeBundle

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 18:15
 * @Description : Description...
 */
class PartitionVpAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 10
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            AIDOL         -> PartitionFragment().makeBundle(TO_PARTITION to AIDOL)
            MAGAZINE      -> PartitionFragment().makeBundle(TO_PARTITION to MAGAZINE)
            THAILAND_SEXY -> PartitionFragment().makeBundle(TO_PARTITION to THAILAND_SEXY)
            CHINESE_SEXY  -> PartitionFragment().makeBundle(TO_PARTITION to CHINESE_SEXY)
            AMERICAN      -> PartitionFragment().makeBundle(TO_PARTITION to AMERICAN)
            JAPAN         -> PartitionFragment().makeBundle(TO_PARTITION to JAPAN)
            CHINA         -> PartitionFragment().makeBundle(TO_PARTITION to CHINA)
            TAIWAN        -> PartitionFragment().makeBundle(TO_PARTITION to TAIWAN)
            THAILAND      -> PartitionFragment().makeBundle(TO_PARTITION to THAILAND)
            KOREA         -> PartitionFragment().makeBundle(TO_PARTITION to KOREA)
            else          -> PartitionFragment()
        }
    }
}