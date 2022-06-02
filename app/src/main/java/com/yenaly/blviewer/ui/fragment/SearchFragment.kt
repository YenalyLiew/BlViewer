package com.yenaly.blviewer.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yenaly.blviewer.databinding.FragmentSearchBinding
import com.yenaly.blviewer.logic.Category
import com.yenaly.blviewer.ui.adapter.PicInfoRvAdapter
import com.yenaly.blviewer.ui.viewmodel.MainViewModel
import com.yenaly.yenaly_libs.base.YenalyFragment
import com.yenaly.yenaly_libs.utils.view.clickTrigger
import com.yenaly.yenaly_libs.utils.view.hideIme
import kotlinx.coroutines.launch

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 16:15
 * @Description : Description...
 */
class SearchFragment : YenalyFragment<FragmentSearchBinding, MainViewModel>() {

    private val searchAdapter by lazy { PicInfoRvAdapter(this) }

    @SuppressLint("SetTextI18n")
    override fun initData(savedInstanceState: Bundle?) {

        binding.sfRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.sfRecyclerView.adapter = searchAdapter

        initClick()

        binding.sfEditText.setOnEditorActionListener { _, _, _ ->
            binding.sfEditText.hideIme(requireActivity().window)
            true
        }
        binding.sfEditText.setOnFocusChangeListener { _, hasFocus ->
            binding.btnScrollView.isVisible = hasFocus
        }
        binding.sfSwipeRefresh.setOnRefreshListener {
            if (viewModel.lastSearchKey != null && viewModel.lastSearchType != null) {
                searchFromBL(viewModel.lastSearchType!!, viewModel.lastSearchKey!!)
            } else {
                binding.sfSwipeRefresh.isRefreshing = false
            }
        }
        lifecycleScope.launch {
            searchAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && searchAdapter.itemCount == 0
                binding.sfRecyclerView.isGone =
                    isListEmpty || loadState.source.refresh is LoadState.Error || loadState.source.refresh is LoadState.Loading
                binding.sfSwipeRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading
                binding.sfErrorTip.isVisible = loadState.source.refresh is LoadState.Error
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.source.refresh as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Log.d("error_tip", it.error.message.toString())
                    binding.sfErrorTip.text = "🥺 ${it.error.message}"
                }
            }
        }
    }

    private fun initClick() {
        binding.btnNormalSearch.clickTrigger(lifecycle) {
            if (binding.sfEditText.textString().isNotBlank()) {
                binding.btnBackRecommend.isGone = false
                searchFromBL(Category.NORMAL, binding.sfEditText.textString())
                binding.sfSwipeRefresh.isGone = false
                viewModel.lastSearchKey = binding.sfEditText.textString()
                viewModel.lastSearchType = Category.NORMAL
            }
            binding.sfEditText.hideIme(requireActivity().window)
            // TODO: hide the recommend and show the recycler view.
        }
        binding.btnTagSearch.clickTrigger(lifecycle) {
            if (binding.sfEditText.textString().isNotBlank()) {
                binding.btnBackRecommend.isGone = false
                searchFromBL(Category.TAG, binding.sfEditText.textString())
                binding.sfSwipeRefresh.isGone = false
                viewModel.lastSearchKey = binding.sfEditText.textString()
                viewModel.lastSearchType = Category.TAG
            }
            binding.sfEditText.hideIme(requireActivity().window)
        }
        binding.btnActorSearch.clickTrigger(lifecycle) {
            if (binding.sfEditText.textString().isNotBlank()) {
                binding.btnBackRecommend.isGone = false
                searchFromBL(Category.ACTOR, binding.sfEditText.textString())
                binding.sfSwipeRefresh.isGone = false
                viewModel.lastSearchKey = binding.sfEditText.textString()
                viewModel.lastSearchType = Category.ACTOR
            }
            binding.sfEditText.hideIme(requireActivity().window)
        }
        binding.btnPublicationSearch.clickTrigger(lifecycle) {
            if (binding.sfEditText.textString().isNotBlank()) {
                binding.btnBackRecommend.isGone = false
                searchFromBL(Category.PUBLICATION, binding.sfEditText.textString())
                binding.sfSwipeRefresh.isGone = false
                viewModel.lastSearchKey = binding.sfEditText.textString()
                viewModel.lastSearchType = Category.PUBLICATION
            }
            binding.sfEditText.hideIme(requireActivity().window)
        }
        binding.btnBackRecommend.setOnClickListener {
            backButtonClickEvent()
        }
    }

    private fun searchFromBL(category: Category, searchKey: String) {
        lifecycleScope.launch {
            viewModel.searchFromBL(category, searchKey).collect {
                binding.sfRecyclerView.scrollToPosition(0)
                searchAdapter.submitData(it)
            }
        }
    }

    private fun backButtonClickEvent() {
        binding.btnBackRecommend.isGone = true
        binding.sfEditText.hideIme(requireActivity().window)
        binding.sfSwipeRefresh.isGone = true
        // TODO: hide the recycler view and show the recommend.
    }

    private fun EditText.textString(): String {
        return this.text.toString()
    }

    // 若正在搜索，则拦截返回按键，使其返回时与点击选单栏返回按钮的行为一致，
    // 返回true，说明已经被处理
    fun onKeyDown(keyCode: Int): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK
            && binding.btnBackRecommend.isVisible
        ) {
            backButtonClickEvent()
            true
        } else false
    }
}