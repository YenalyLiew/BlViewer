package com.yenaly.yenaly_libs.utils;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * @ProjectName : YenalyModule
 * @Author : Yenaly Liew
 * @Time : 2022/04/18 018 17:00
 * @Description : Description...
 */
public class TextUtil {

    /**
     * 把一个数转化为带万、亿的格式，常用于视频播放量等。
     * 例如：102002 -> 10.2万
     *
     * @param playCount 播放量
     * @return 简化格式
     */
    public static String toPlayCountCase(long playCount) {
        String count;
        if (playCount < 0) {
            count = "0";
        } else if (playCount < 1_0000) {
            count = String.valueOf(playCount);
        } else if (playCount < 1_0000_0000) {
            count = String.format(
                    Locale.getDefault(),
                    "%d.%02d万",
                    playCount / 1_0000,
                    playCount % 1_0000 / 100
            );
        } else {
            count = String.format(
                    Locale.getDefault(),
                    "%d.%02d亿",
                    playCount / 1_0000_0000,
                    playCount % 1_0000_0000 / 100_0000
            );
        }
        return count;
    }

    /**
     * 把一个秒数转化为时间格式，常用于视频持续时间。
     * 例如：123(s) -> 02:03
     *
     * @param time 秒
     * @return 时间格式
     */
    @NonNull
    public static String secondToTimeCase(long time) {
        long second = time % 60;
        long minute = time / 60;
        long hour = 0;
        if (minute >= 60) {
            hour = minute / 60;
            minute %= 60;
        }
        String secondString;
        String minuteString;
        String hourString;
        if (second < 10) secondString = "0" + second;
        else secondString = Long.toString(second);
        if (minute < 10) minuteString = "0" + minute;
        else minuteString = Long.toString(minute);
        if (hour < 10) hourString = "0" + hour;
        else hourString = Long.toString(hour);
        String timeCaseString;
        if (hour != 0) timeCaseString = hourString + ":" + minuteString + ":" + secondString;
        else timeCaseString = minuteString + ":" + secondString;
        return timeCaseString;
    }
}
