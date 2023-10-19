package com.example.auto_moto

import android.service.autofill.UserData
import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object ValidationUtils {

    fun isTextNotEmpty(text: String): Boolean {
        return !TextUtils.isEmpty(text)
    }

}