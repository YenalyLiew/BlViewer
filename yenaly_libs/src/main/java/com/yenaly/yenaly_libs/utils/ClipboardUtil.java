package com.yenaly.yenaly_libs.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName : YenalyModule
 * @Author : Yenaly Liew
 * @Time : 2022/04/19 019 10:40
 * @Description : Description...
 */
public class ClipboardUtil {

    /**
     * 将文字复制到剪切板
     *
     * @param context 上下文
     * @param label   为此文字设置的用户可见的标签 (optional)
     * @param text    要复制的文字
     */
    public static void copyTextToClipboard(
            @NonNull Context context,
            @Nullable CharSequence label,
            @Nullable CharSequence text
    ) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(label, text);
        clipboardManager.setPrimaryClip(clipData);
    }

    /**
     * 剪贴板中最近一次的内容
     *
     * @param context 上下文
     * @return 剪贴板中最近一次的内容
     */
    @Nullable
    public static String getTextFromClipboard(@NonNull Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (null != clipboardManager) {
            ClipData clipData = clipboardManager.getPrimaryClip();
            if (null != clipData && clipData.getItemCount() > 0) {
                ClipData.Item item = clipData.getItemAt(0);
                if (null != item) {
                    CharSequence charSequence = item.coerceToText(context);
                    if (null != charSequence) {
                        return charSequence.toString();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 清除剪切板内容
     *
     * @param context 上下文
     */
    public static void clearClipBoard(@NonNull Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (null != clipboardManager) {
            try {
                ClipData clipData = ClipData.newPlainText(null, null);
                clipboardManager.setPrimaryClip(clipData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
