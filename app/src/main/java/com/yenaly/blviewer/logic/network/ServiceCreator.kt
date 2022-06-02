package com.yenaly.blviewer.logic.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @ProjectName : BlViewer
 * @Author : Yenaly Liew
 * @Time : 2022/05/27 027 16:57
 * @Description : Description...
 */
object ServiceCreator {
    inline fun <reified T> create(baseUrl: String): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient())
        .build()
        .create(T::class.java)

    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}