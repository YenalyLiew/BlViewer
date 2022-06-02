package com.yenaly.yenaly_libs.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.pm.PackageInfoCompat;

/**
 * @ProjectName : YenalyModule
 * @Author : Yenaly Liew
 * @Time : 2022/04/19 019 13:01
 * @Description : Description...
 */
public class AppUtil {

    /**
     * 获取本地APP版本号，获取失败则返回null
     *
     * @param context 上下文
     * @return 版本号，例如 1.0.0
     */
    @Nullable
    public static String getAppLocalVersion(@NonNull Context context) {
        String version;
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    0
            ).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = null;
        }
        return version;
    }

    /**
     * 获取本地APP版本代码，获取失败则返回0
     *
     * @param context 上下文
     * @return 版本代码，例如 12314355
     */
    public static long getAppLocalVersionCode(@NonNull Context context) {
        long versionCode;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    0
            );
            versionCode = PackageInfoCompat.getLongVersionCode(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            versionCode = 0;
        }
        return versionCode;
    }
}