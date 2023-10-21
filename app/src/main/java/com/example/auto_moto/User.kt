package com.example.auto_moto

data class User(
    val email: String,
    val username: String,
    val contact: Int,
    val password: String,
    val confirmPassword: String,
    val resetCode: String,
    val profileImageURL: String
)