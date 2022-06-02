package com.yenaly.blviewer.ui.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.ItemPartitionPicBinding
import com.yenaly.blviewer.logic.ALBUM_TITLE
import com.yenaly.blviewer.logic.APP_NAME
import com.yenaly.blviewer.logic.LEG_CODE
import com.yenaly.blviewer.logic.model.BlSearchModel
import com.yenaly.blviewer.logic.network.BlNetwork
import com.yenaly.blviewer.ui.activity.PicDetailActivity
import com.yenaly.yenaly_libs.utils.*

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 22:11
 * @Description : Description...
 */
class PicInfoRvAdapter(private val fragment: Fragment) :
    PagingDataAdapter<BlSearchModel, PicInfoRvAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<BlSearchModel>() {
            override fun areItemsTheSame(
                oldItem: BlSearchModel,
                newItem: BlSearchModel
            ): Boolean {
                return oldItem.imgCode == oldItem.imgCode
            }

            override fun areContentsTheSame(
                oldItem: BlSearchModel,
                newItem: BlSearchModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(view: View) : BaseDataBindingHolder<ItemPartitionPicBinding>(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.dataBinding?.let {
            it.imgTitle.text = item.imgTitle
            it.imgCode.text = item.imgCode
            it.imgCode.setTextColor(Color.BLUE)

            Glide.with(fragment).load("${BlNetwork.BEAUTIFUL_LEG_CDN_URL}${item.imgThumbnail}")
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
                        val layoutParams = it.imgSrc.layoutParams as FrameLayout.LayoutParams
                        val itemWidth = appScreenWidth / 2 - 4.dp * 5
                        layoutParams.width = itemWidth
                        if (resource != null) {
                            val scale = (itemWidth + 0F) / resource.intrinsicWidth
                            layoutParams.height = (resource.intrinsicHeight * scale).toInt()
                        } else {
                            layoutParams.height = (itemWidth * 1.5).toInt()
                        }
                        it.imgSrc.layoutParams = layoutParams
                        return false
                    }
                })
                .into(it.imgSrc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_partition_pic, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.dataBinding?.let {
            it.imgCode.setOnLongClickListener {
                val position = viewHolder.bindingAdapterPosition
                val item = getItem(position)!!
                item.imgCode.copyToClipboard()
                Toast.makeText(applicationContext, "$APP_NAME: 图集代码已复制到剪贴板", Toast.LENGTH_SHORT)
                    .show()
                true
            }
        }
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val item = getItem(position)!!
            //val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
            //    fragment.requireActivity(),
            //    viewHolder.dataBinding!!.imgSrc,
            //    fragment.getString(R.string.app_name)
            //).toBundle()
            fragment.startActivity<PicDetailActivity>(
                values = arrayOf(
                    LEG_CODE to item.imgCode,
                    ALBUM_TITLE to item.imgTitle
                )
                //extra = bundle
            )
        }
        return viewHolder
    }
}