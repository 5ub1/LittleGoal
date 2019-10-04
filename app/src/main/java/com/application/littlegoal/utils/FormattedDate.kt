package com.application.littlegoal.utils

import java.text.SimpleDateFormat
import java.util.*


public class FormattedDate {
    private val sdf = SimpleDateFormat("yyyyMMdd", Locale.CHINA)
    fun getCurrentDate(): String {
        val curDate = Calendar.getInstance()
        return sdf.format(curDate)
    }
}