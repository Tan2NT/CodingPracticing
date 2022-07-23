package com.tanhoang.com.utils

import java.util.*

object Utils {
    fun sleep(duration: Long) {
        val startTime = Date().time
        var now = Date().time
        while (now - startTime < duration)
            now = Date().time
    }
}