package com.hzz.calendar.data

data class CalendarEventInfo(
    val title: String = "", // 标题
    val description: String = "",   // 描述
    val startTimestamp: Long = 1733639400584, // 开始时间
    val lastTime: Int = 10, // 持续时间
    val remindTimeBeforeStart: Int = 0, // 提前提醒时间
    val frequencyUnit: Int = -1, // 频率单位, -1 一次性事件， 0 天， 1 周， 2 月， 3 年
    val frequencyValue: Int = 1, // 频率值
)
