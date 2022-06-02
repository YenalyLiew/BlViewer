package com.yenaly.blviewer.logic

const val APP_NAME = "BlViewer"

// (https://cdn.beatifulleg.com/)'s length
const val MAGIC_NUMBER = 28

const val LEG_CODE = "leg_code"
const val ALBUM_TITLE = "album_title"
const val ALBUM_COVER = "album_cover"

const val TO_PIC_VIEW_POSITION = "pic_view_position"
const val TO_PIC_VIEW_URLS = "pic_view_urls"

const val TO_PARTITION = "which"
const val AIDOL = 0
const val MAGAZINE = 1
const val THAILAND_SEXY = 2
const val CHINESE_SEXY = 3
const val AMERICAN = 4
const val JAPAN = 5
const val CHINA = 6
const val TAIWAN = 7
const val THAILAND = 8
const val KOREA = 9

enum class Partition(val webName: String) {
    AIDOL("aidol"),
    MAGAZINE("magazine"),
    THAILAND_SEXY("thailand"),
    CHINESE_SEXY("chinese"),
    AMERICAN("european-and-american-beauty"),
    JAPAN("japan-s-beauty"),
    CHINA("chinese-mainland-beauties"),
    TAIWAN("taiwan-beauty"),
    THAILAND("thai-beauty"),
    KOREA("south-korea-beauty");
}

enum class Category(val webName: String) {
    NORMAL(""),
    TAG("tag"),
    ACTOR("actor"),
    PUBLICATION("publication")
}