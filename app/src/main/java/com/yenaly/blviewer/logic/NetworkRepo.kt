package com.yenaly.blviewer.logic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.yenaly.blviewer.logic.model.BlAlbumModel
import com.yenaly.blviewer.logic.network.BlNetwork
import com.yenaly.blviewer.logic.network.PartitionPagingSource
import com.yenaly.blviewer.logic.network.SearchPagingSource
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 20:54
 * @Description : Description...
 */
object NetworkRepo {

    fun getPartitionInfoFromBL(partition: String) = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PartitionPagingSource(partition)
        }
    ).flow

    fun searchFromBL(category: Category, searchKey: String) = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            SearchPagingSource(category, searchKey)
        }
    ).flow

    fun loadAlbumWebFromBL(legCode: String) = flow {
        try {
            val responseBody = BlNetwork.legNetwork.loadAlbumWebFromBL(legCode)
            val bodyStringResult = runCatching {
                responseBody.string()
            }
            val bodyString = bodyStringResult.getOrNull()
            if (bodyString != null) {
                val jsoup = Jsoup.parse(bodyString)
                val body = jsoup.body()
                val figureClasses = body.select("figure[class=album-photo]")
                val divClasses = body.select("div[class=details-box-inner]")
                var title = "Title Not Found"
                val urlWithDescList = mutableListOf<BlAlbumModel.Pic>()
                when {
                    figureClasses.isNotEmpty() -> {
                        title = figureClasses[0].select("img")[0].attr("title")
                        figureClasses.forEachIndexed { index, element ->
                            val picUrl = element.select("img")[0].attr("src")
                            val picDesc = element.select("figcaption")[0].text()
                            urlWithDescList.add(BlAlbumModel.Pic(picUrl, picDesc, index))
                        }
                    }
                    divClasses.isNotEmpty() -> {
                        title = divClasses[0].select("img[class]")[0].attr("title")
                        val picElements = divClasses[0].select("img[src~=^https]")
                        picElements.forEachIndexed { index, element ->
                            val picUrl = element.attr("src")
                            urlWithDescList.add(BlAlbumModel.Pic(picUrl, null, index))
                        }
                    }
                    else -> emit(Result.failure(RuntimeException("涩图代号有误，请重新搜索")))
                }
                val tagsList = mutableListOf<String>()
                val tags = body.getElementsByClass("tags")[0].select("a")
                tags.forEach { tag ->
                    tagsList.add(tag.text())
                }
                emit(Result.success(BlAlbumModel(legCode, title, tagsList, urlWithDescList)))
            } else {
                emit(Result.failure(RuntimeException("Oops... Body is null.")))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }
}