package com.yenaly.blviewer.logic.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yenaly.blviewer.logic.Category
import com.yenaly.blviewer.logic.MAGIC_NUMBER
import com.yenaly.blviewer.logic.model.BlSearchModel
import org.jsoup.Jsoup

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/29 029 10:51
 * @Description : Description...
 */
class SearchPagingSource(
    private val category: Category,
    private val searchKey: String
) : PagingSource<Int, BlSearchModel>() {
    override fun getRefreshKey(state: PagingState<Int, BlSearchModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BlSearchModel> {
        try {
            val page = params.key ?: 1
            val searchList = mutableListOf<BlSearchModel>()
            val responseBody = when (category) {
                Category.NORMAL -> BlNetwork.legNetwork.searchFromBL(page, searchKey)
                Category.ACTOR -> BlNetwork.legNetwork.categorySearchFromBL(
                    Category.ACTOR.webName,
                    searchKey,
                    page
                )
                Category.TAG -> BlNetwork.legNetwork.categorySearchFromBL(
                    Category.TAG.webName,
                    searchKey,
                    page
                )
                Category.PUBLICATION -> BlNetwork.legNetwork.categorySearchFromBL(
                    Category.PUBLICATION.webName,
                    searchKey,
                    page
                )
            }
            val bodyStringResult = runCatching {
                responseBody.string()
            }
            val bodyString = bodyStringResult.getOrNull()
            if (bodyString != null) {
                val jsoup = Jsoup.parse(bodyString)
                val body = jsoup.body()
                val postIds = body.select("div[id~=^post-]")
                postIds.forEach { postId ->
                    val imgTitle = postId.selectFirst("img")!!.attr("title")
                    val imgUrl = postId.selectFirst("a")!!.attr("href")
                    val imgCode = imgUrl.substring(MAGIC_NUMBER)
                    val imgThumbnail =
                        postId.selectFirst("img")!!.attr("src").substring(MAGIC_NUMBER)
                    searchList.add(BlSearchModel(imgTitle, imgUrl, imgCode, imgThumbnail))
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (postIds.isNotEmpty()) page + 1 else null
                return LoadResult.Page(searchList, prevKey, nextKey)
            } else {
                return LoadResult.Error(RuntimeException("Oops... Body is null."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}