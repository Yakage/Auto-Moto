package com.example.auto_moto

import android.text.TextUtils

object ValidationUtils {

    fun isTextNotEmpty(text: String): Boolean {
        return !TextUtils.isEmpty(text)
    }

}