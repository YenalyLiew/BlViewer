package com.yenaly.blviewer.ui.adapter

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.ItemPicViewBinding
import com.yenaly.blviewer.logic.model.BlAlbumModel
import com.yenaly.blviewer.ui.activity.PicViewActivity
import com.yenaly.yenaly_libs.utils.SystemStatusUtil
import com.yenaly.yenaly_libs.utils.setSystemBarIconLightMode

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/06/01 001 23:14
 * @Description : Description...
 */
class PicViewVpAdapter(private val activity: PicViewActivity) :
    BaseQuickAdapter<BlAlbumModel.Pic, PicViewVpAdapter.ViewHolder>(R.layout.item_pic_view) {

    inner class ViewHolder(view: View) : BaseDataBindingHolder<ItemPicViewBinding>(view)

    override fun convert(holder: ViewHolder, item: BlAlbumModel.Pic) {
        Glide.with(context).load(item.url).into(holder.dataBinding!!.photoView)
    }

    override fun onItemViewHolderCreated(viewHolder: ViewHolder, viewType: Int) {
        val fullToolbar: View = activity.findViewById(R.id.pva_full_toolbar)
        viewHolder.dataBinding!!.photoView.setOnPhotoTapListener { _, _, _ ->
            fullToolbar.isInvisible = fullToolbar.isVisible
            SystemStatusUtil.fullScreen(activity.window, true, true)
            activity.window.setSystemBarIconLightMode(fullToolbar.isInvisible)
        }
    }
}