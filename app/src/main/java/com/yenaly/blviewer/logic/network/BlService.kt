package com.yenaly.blviewer.logic.network

import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 16:56
 * @Description : Description...
 */
interface BlService {
    @GET("{partition}/page/{page}")
    suspend fun getPartitionInfoFromBL(
        @Path("partition") partition: String,
        @Path("page") page: Int
    ): ResponseBody

    @GET("page/{page}")
    suspend fun searchFromBL(
        @Path("page") page: Int,
        @Query("s") searchKey: String
    ): ResponseBody

    @GET("{category}/{search_key}/page/{page}")
    suspend fun categorySearchFromBL(
        @Path("category") category: String,
        @Path("search_key") searchKey: String,
        @Path("page") page: Int
    ): ResponseBody

    @GET
    suspend fun loadAlbumWebFromBL(
        @Url legCode: String
    ): ResponseBody

    @Headers(
        "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36"
    )
    @GET
    suspend fun loadPicFromBL(
        @Url picCode: String
    ): ResponseBody
}