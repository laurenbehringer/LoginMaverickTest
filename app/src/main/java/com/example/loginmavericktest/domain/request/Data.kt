package com.example.loginmavericktest.domain.request


import com.google.gson.annotations.SerializedName

data class Data(
    val cifnum: String,
    val click_action: String,
    val navigations: String,
    val username: String
)