package com.yenaly.blviewer.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.ActivityMainBinding
import com.yenaly.blviewer.ui.fragment.MainFragment
import com.yenaly.blviewer.ui.fragment.PersonalCenterFragment
import com.yenaly.blviewer.ui.fragment.SearchFragment
import com.yenaly.blviewer.ui.viewmodel.MainViewModel
import com.yenaly.yenaly_libs.base.YenalyActivity
import com.yenaly.yenaly_libs.utils.setSystemBarIconLightMode
import com.yenaly.yenaly_libs.utils.view.BottomNavigationViewMediator
import com.yenaly.yenaly_libs.utils.view.realOverScrollMode

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 16:15
 * @Description : Description...
 */
class MainActivity : YenalyActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var mediator: BottomNavigationViewMediator

    override fun setUiStyle() {
        window.setSystemBarIconLightMode(statusBar = true)
    }

    override fun initData(savedInstanceState: Bundle?) {
        binding.mainViewPager.realOverScrollMode = View.OVER_SCROLL_NEVER
        mediator = BottomNavigationViewMediator(
            binding.mainBnv, binding.mainViewPager, listOf(
                R.id.nav_pic_album to MainFragment(),
                R.id.nav_search to SearchFragment(),
                R.id.nav_personal_center to PersonalCenterFragment()
            )
        ).attach()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val currentFragment = mediator.currentFragment
        if (currentFragment is SearchFragment) {
            val fragmentKeyDown = currentFragment.onKeyDown(keyCode)
            // 如果返回true，说明处于正在搜索事件，拦截返回键。若false，则不拦截，交给超类处理
            return if (fragmentKeyDown) true else super.onKeyDown(keyCode, event)
        }
        return super.onKeyDown(keyCode, event)
    }
}