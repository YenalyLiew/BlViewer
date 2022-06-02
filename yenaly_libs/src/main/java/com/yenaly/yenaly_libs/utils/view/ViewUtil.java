package com.yenaly.yenaly_libs.utils.view;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

/**
 * @ProjectName : YenalyModule
 * @Author : Yenaly Liew
 * @Time : 2022/04/18 018 19:21
 * @Description : Description...
 */
public class ViewUtil {

    /**
     * 设置view的margins，设置相对位置margins更符合Google标准
     *
     * @param view   修改margins的view
     * @param start  view的开始位
     * @param top    view的顶部
     * @param end    view的结束位
     * @param bottom view的底部
     */
    public static void setMargins(@NonNull View view, int start, int top, int end, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(0, top, 0, bottom);
            p.setMarginStart(start);
            p.setMarginEnd(end);
            view.requestLayout();
        }
    }
}
