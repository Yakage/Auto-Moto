package com.example.auto_moto

import android.content.Intent
import android.content.ActivityNotFoundException
import android.content.Context

class EmailService {
    companion object {
        fun sendEmail(context: Context, emailIntent: Intent) {
            try {
                context.startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                // Handle the case where there is no email app installed or registered to handle the intent.
            }
        }
    }
}