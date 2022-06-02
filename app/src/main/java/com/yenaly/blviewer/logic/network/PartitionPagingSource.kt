package com.yenaly.blviewer.logic.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yenaly.blviewer.logic.MAGIC_NUMBER
import com.yenaly.blviewer.logic.model.BlSearchModel
import org.jsoup.Jsoup

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 21:45
 * @Description : Description...
 */
class PartitionPagingSource(
    private val partition: String
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
            val partitionPicList = mutableListOf<BlSearchModel>()
            val responseBody = BlNetwork.legNetwork.getPartitionInfoFromBL(partition, page)
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
                    partitionPicList.add(BlSearchModel(imgTitle, imgUrl, imgCode, imgThumbnail))
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (postIds.isNotEmpty()) page + 1 else null
                return LoadResult.Page(partitionPicList, prevKey, nextKey)
            } else {
                return LoadResult.Error(RuntimeException("Oops... Body is null."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}