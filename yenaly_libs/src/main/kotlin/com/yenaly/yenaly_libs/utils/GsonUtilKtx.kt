package com.yenaly.yenaly_libs.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> fromJson(json: String): T {
    return GsonUtilKtx.gson.fromJson(json, object : TypeToken<T>() {}.type)
}

@JvmName("stringFromJson")
inline fun <reified T> String.fromJson(): T {
    return GsonUtilKtx.gson.fromJson(this, object : TypeToken<T>() {}.type)
}

fun Any.toJson(): String {
    return GsonUtilKtx.gson.toJson(this)
}

object GsonUtilKtx {
    @JvmSynthetic
    @JvmField
    val gson = Gson()
}