package com.yenaly.blviewer.ui.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.ItemPicDetailBinding
import com.yenaly.blviewer.logic.model.BlAlbumModel
import com.yenaly.yenaly_libs.utils.appScreenWidth
import com.yenaly.yenaly_libs.utils.dp

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/30 030 17:35
 * @Description : Description...
 */
class PicDetailRvAdapter :
    BaseQuickAdapter<BlAlbumModel.Pic, PicDetailRvAdapter.ViewHolder>(R.layout.item_pic_detail) {

    inner class ViewHolder(view: View) : BaseDataBindingHolder<ItemPicDetailBinding>(view) {
        val itemWidth = (appScreenWidth - 8 * 4.dp) / 2
        val itemHeight = itemWidth
    }

    @SuppressLint("SetTextI18n")
    override fun convert(holder: ViewHolder, item: BlAlbumModel.Pic) {
        holder.dataBinding?.let {
            val layoutParams = it.picDetail.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = holder.itemWidth
            layoutParams.height = holder.itemHeight
            it.picDetail.layoutParams = layoutParams

            it.picPosition.text = (item.position + 1).toString()

            Glide.with(context).load(item.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.let { drawable ->
                            if (drawable.intrinsicHeight * 1.0 / drawable.intrinsicWidth < 1.2) {
                                it.picDetail.scaleType = ImageView.ScaleType.FIT_CENTER
                            } else {
                                it.picDetail.scaleType = ImageView.ScaleType.FIT_START
                            }
                        }
                        return false
                    }
                })
                .into(it.picDetail)
        }
    }
}