package com.yenaly.blviewer.ui.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.ActivityPicViewBinding
import com.yenaly.blviewer.logic.TO_PIC_VIEW_POSITION
import com.yenaly.blviewer.logic.TO_PIC_VIEW_URLS
import com.yenaly.blviewer.logic.model.BlAlbumModel
import com.yenaly.blviewer.ui.adapter.PicViewVpAdapter
import com.yenaly.blviewer.ui.viewmodel.PicViewViewModel
import com.yenaly.yenaly_libs.base.YenalyActivity
import com.yenaly.yenaly_libs.utils.intentExtra
import com.yenaly.yenaly_libs.utils.statusBarHeight

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/06/01 001 22:59
 * @Description : Description...
 */
class PicViewActivity : YenalyActivity<ActivityPicViewBinding, PicViewViewModel>() {

    private val picViewAdapter by lazy { PicViewVpAdapter(this) }
    private val position by intentExtra(TO_PIC_VIEW_POSITION, 0)
    private val urlsList by intentExtra(TO_PIC_VIEW_URLS, emptyList<BlAlbumModel.Pic>())

    override fun initData(savedInstanceState: Bundle?) {

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        binding.pvaViewPager.adapter = picViewAdapter
        picViewAdapter.setList(urlsList)
        binding.pvaViewPager.setCurrentItem(position, false)
        val emptyLayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight)
        binding.emptyView.layoutParams = emptyLayoutParams

        val totalPage = urlsList.size
        binding.picPosition.text = getString(R.string.pic_view_position, position + 1, totalPage)
        binding.pvaViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.picPosition.text =
                        getString(R.string.pic_view_position, position + 1, totalPage)
                }
            }
        )

        binding.btnBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onNavigateUp(): Boolean {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return super.onNavigateUp()
    }
}