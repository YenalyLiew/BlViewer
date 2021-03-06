package com.yenaly.blviewer.ui.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.yenaly.blviewer.R
import com.yenaly.blviewer.databinding.ActivityPicDetailBinding
import com.yenaly.blviewer.logic.*
import com.yenaly.blviewer.ui.adapter.PicDetailRvAdapter
import com.yenaly.blviewer.ui.viewmodel.PicDetailViewModel
import com.yenaly.yenaly_libs.base.YenalyActivity
import com.yenaly.yenaly_libs.utils.copyToClipboard
import com.yenaly.yenaly_libs.utils.intentExtra
import com.yenaly.yenaly_libs.utils.setSystemBarIconLightMode
import com.yenaly.yenaly_libs.utils.startActivity
import com.yenaly.yenaly_libs.utils.view.AppBarLayoutStateChangeListener
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/30 030 16:11
 * @Description : Description...
 */
class PicDetailActivity : YenalyActivity<ActivityPicDetailBinding, PicDetailViewModel>() {

    private val picDetailAdapter by lazy { PicDetailRvAdapter() }
    private val legCode by intentExtra<String>(LEG_CODE)
    private val albumTitle by intentExtra<String>(ALBUM_TITLE)

    override fun setUiStyle() {
        window.setSystemBarIconLightMode(statusBar = true)
    }

    override fun initData(savedInstanceState: Bundle?) {
        binding.pdaRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.pdaRecyclerView.adapter = picDetailAdapter

        initToolbar()

        if (albumTitle != null) {
            binding.albumTitle.text = albumTitle
            binding.albumTitleTop.text = albumTitle
        }
        Log.d("legCode", legCode.toString())
        if (legCode != null) {
            viewModel.loadAlbumWebFromBL(legCode!!)
        } else {
            Toast.makeText(applicationContext, "?????????????????????", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }

    override fun liveDataObserve() {
        viewModel.albumLiveData.observe(this) { result ->
            val picInfo = result.getOrNull()
            if (picInfo != null) {
                picDetailAdapter.setList(picInfo.pic)
                binding.btnWatch.setOnClickListener {
                    startActivity<PicViewActivity>(
                        TO_PIC_VIEW_POSITION to 0,
                        TO_PIC_VIEW_URLS to picInfo.pic
                    )
                }
                binding.btnDownload.setOnClickListener {
                    Snackbar.make(binding.coordinatorLayout, "?????????????????????", Snackbar.LENGTH_SHORT)
                        .show()
                    // TODO: download
                }
                picDetailAdapter.setOnItemClickListener { _, _, position ->
                    startActivity<PicViewActivity>(
                        TO_PIC_VIEW_POSITION to position,
                        TO_PIC_VIEW_URLS to picInfo.pic
                    )
                }
                if (albumTitle == null) {
                    binding.albumTitle.text = picInfo.title
                    binding.albumTitleTop.text = picInfo.title
                }
                binding.tagCardView.isInvisible = false
                if (binding.tagGroup.childCount == 0) {
                    binding.tagGroup.createChips(picInfo.tags,
                        onLongClick = { _, text ->
                            text.copyToClipboard()
                            Toast.makeText(this, "$APP_NAME: TAG?????????????????????", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                Glide.with(this).load(picInfo.pic[0].url)
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
                            Glide.with(this@PicDetailActivity)
                                .load(resource)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .apply(RequestOptions.bitmapTransform(BlurTransformation(15)))
                                .into(binding.blurAlbumCover)
                            return false
                        }
                    })
                    .into(binding.albumCover)
            } else {
                result.exceptionOrNull()?.message?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initToolbar() {
        binding.btnBack.setOnClickListener { finish() }
        binding.pdaAppBar.addOnOffsetChangedListener(object : AppBarLayoutStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.COLLAPSED -> binding.albumTitleTop.isInvisible = false
                    else -> binding.albumTitleTop.isInvisible = true
                }
            }
        })
    }

    private fun ChipGroup.createChips(
        texts: List<String>,
        onClick: ((Chip, String) -> Unit)? = null,
        onLongClick: ((Chip, String) -> Unit)? = null
    ) {
        for (text in texts) {
            val chipView = layoutInflater.inflate(R.layout.item_tag_chip, this, false) as Chip
            chipView.text = text
            chipView.setOnClickListener { onClick?.invoke(it as Chip, text) }
            chipView.setOnLongClickListener {
                onLongClick?.invoke(it as Chip, text)
                true
            }
            binding.tagGroup.addView(chipView)
        }
    }
}